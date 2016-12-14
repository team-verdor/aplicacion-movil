package com.verdor.verdormobile.net;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Api {


/*
    HashMap<Integer, Tag> bufferedTags;
    HashMap<Integer, Content> bufferedContent;

    NetProvider net;
    Activity activity;

    public ContentProvider(NetProvider net, Activity activity) {
        this.net = net;
        this.activity = activity;

        bufferedContent = new HashMap<Integer, Content>();
        bufferedTags = new HashMap<Integer, Tag>();
    }

    public ArrayList<Content> getContents(int[] ids) {
        ArrayList<Content> cont = new ArrayList<>();
        StringBuilder string = new StringBuilder();
        for (int i : ids) {
            if (bufferedContent.containsKey(i)) {
                cont.add(bufferedContent.get(i));
            } else {
                string.append(i + ",");
            }
        }
        if (string.length() > 1) {
            string.deleteCharAt(string.length() - 1);
        }

        try {
            JSONObject json = new JSONObject(net.requestString("get/content", string.toString()));
            ArrayList<Content> res = parseGetContent(json.getJSONArray("content"));
            ArrayList<Tag> tag = parseGetTag(json.getJSONArray("tag"));

            for (Content a : res) {
                cont.add(a);
            }

            updateTagBuffer(tag);
            updateContentBuffer(res);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cont;
    }

    public ArrayList<Content> filterByTag(Tag tag) {
        ArrayList tempArrLst = new ArrayList<Tag>();
        tempArrLst.add(tag);

        return filterByTag(tempArrLst);
    }

    public ArrayList<Content> filterByTag(ArrayList<Tag> tags) {
        ArrayList<Content> cont = new ArrayList<>();
        StringBuilder string = new StringBuilder();

        for (Tag t : tags) {
            string.append(t.getId() + ",");
        }

        if (string.length() > 1) {
            string.deleteCharAt(string.length() - 1);
        }

        try {
            JSONObject json = new JSONObject(net.requestString("search/content/bytag", string.toString()));
            ArrayList<Content> res = parseGetContent(json.getJSONArray("content"));
            ArrayList<Tag> tag = parseGetTag(json.getJSONArray("tag"));

            for (Content a : res) {
                cont.add(a);
            }

            updateTagBuffer(tag);
            updateContentBuffer(res);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cont;
    }

    public ArrayList<Content> search(String param) {
        ArrayList<Content> cont = new ArrayList<>();

        try {
            JSONObject json = new JSONObject(net.requestString("search/content", param));
            ArrayList<Content> res = parseGetContent(json.getJSONArray("content"));
            ArrayList<Tag> tag = parseGetTag(json.getJSONArray("tag"));

            for (Content a : res) {
                cont.add(a);
            }

            updateTagBuffer(tag);
            updateContentBuffer(res);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cont;
    }

    //<editor-fold desc="Get tag functions">
    public Tag getTag(int id) {
        ArrayList<Tag> tag = new ArrayList<>();

        if (bufferedTags.containsKey(id)) {
            return bufferedTags.get(id);
        }

        getTags(tag, id + "");
        if (tag.size() > 0) {
            Tag t = tag.get(0);
            bufferedTags.put(t.getId(), t);
            return t;
        }

        return null;
    }

    public ArrayList<Tag> getTags(int[] ids) {
        StringBuilder string = new StringBuilder();
        ArrayList<Tag> tag = new ArrayList<>();

        for (int i : ids) {
            if (bufferedTags.containsKey(i)) {
                tag.add(bufferedTags.get(i));
            } else {
                string.append(i + ",");
            }
        }

        if (string.length() > 1) {
            string.deleteCharAt(string.length() - 1);
        } else {
            return tag;
        }

        getTags(tag, string.toString());
        return tag;
    }

    public ArrayList<Tag> getTags(ArrayList<Tag> ids) {
        StringBuilder string = new StringBuilder();
        ArrayList<Tag> tag = new ArrayList<>();

        for (Tag i : ids) {
            if (bufferedTags.containsKey(i)) {
                tag.add(bufferedTags.get(i));
            } else {
                string.append(i.getId() + ",");
            }
        }

        if (string.length() > 1) {
            string.deleteCharAt(string.length() - 1);
        } else {
            return tag;
        }

        getTags(tag, string.toString());
        return tag;
    }

    public void getTags(ArrayList<Tag> tag, String values) {
        try {
            JSONArray json = new JSONArray(net.requestString("get/tag", values.toString()));
            tag.addAll(parseGetTag(json));

            updateTagBuffer(tag);
        } catch (JSONException e) {
            System.out.println(e);
        }
    }
    //</editor-fold>

    //<editor-fold desc="Parse request">
    private ArrayList<Content> parseGetContent(JSONArray jsonRoot)
            throws JSONException {

        ArrayList<Content> contentArray = new ArrayList<>();

        for (int i = 0; i < jsonRoot.length(); i++) {
            JSONObject jsonContent = jsonRoot.getJSONObject(i);
            contentArray.add(parseContent(jsonContent));
        }

        return contentArray;
    }

    private ArrayList<Tag> parseGetTag(JSONArray jsonRoot)
            throws JSONException {

        ArrayList<Tag> tagArray = new ArrayList<>();

        for (int i = 0; i < jsonRoot.length(); i++) {
            JSONObject jsonContent = jsonRoot.getJSONObject(i);
            tagArray.add(parseTag(jsonContent));
        }

        return tagArray;
    }

    private Content parseContent(JSONObject jsonContent) throws JSONException {
        Content content = new Content(jsonContent.getInt(Content.FIELD_NAME_ID));

        content.setTitulo(jsonContent.getString(Content.FIELD_NAME_TITULO));
        content.setSubtitulo(jsonContent.getString(Content.FIELD_NAME_SUBTITULO));
        content.setDescripcion(jsonContent.getString(Content.FIELD_NAME_DESCRIPCION));
        content.setContenido(jsonContent.getString(Content.FIELD_NAME_CONTENIDO));
        content.setNombreArchivo(jsonContent.getString(Content.FIELD_NAME_FILENAME));
        content.setRuta(jsonContent.getString(Content.FIELD_NAME_PATH));
        content.setTamano(jsonContent.getInt(Content.FIELD_NAME_TAMANO));
        content.setTipo(jsonContent.getInt(Content.FIELD_NAME_TIPO));

        JSONArray jsonTags = jsonContent.getJSONArray(Content.FIELD_NAME_TAGS);

        ArrayList<Integer> tags = content.getTags();
        for (int e = 0; e < jsonTags.length(); e++) {
            tags.add(jsonTags.getInt(e));
        }
        return content;
    }

    private Tag parseTag(JSONObject jsonContent) throws JSONException {
        Tag tag = new Tag(jsonContent.getInt(Tag.FIELD_NAME_ID));
        tag.setNombre(jsonContent.getString(Tag.FIELD_NAME_NOMBRE));

        return tag;
    }
    //</editor-fold>

    private void updateTagBuffer(ArrayList<Tag> tag) {
        for (Tag a : tag) {
            bufferedTags.put(a.getId(), a);
        }
    }

    private void updateContentBuffer(ArrayList<Content> res) {
        for (Content a : res) {
            bufferedContent.put(a.getId(), a);
        }
    }*/
}