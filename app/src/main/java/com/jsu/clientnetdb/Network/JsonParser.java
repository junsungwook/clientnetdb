package com.jsu.clientnetdb.Network;

import com.jsu.clientnetdb.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParser {
    static public int getUserInfoJson(String response, ArrayList<UserInfo> userList) throws JSONException{
        String strID;
        String strName;
        String strPhone;
        String strGrade;
        String strWriteTime;

        JSONObject rootJSON = new JSONObject(response);
        JSONArray jsonArray = new JSONArray(rootJSON.getString("datas"));

        for (int i=0; i<jsonArray.length();i++){
            JSONObject jsonObj = (JSONObject)jsonArray.get(i);
            if(jsonObj.getString("ID").equals("null"))
                strID="-";
            else
                strID=jsonObj.getString("ID");

            if(jsonObj.getString("NAME").equals("null"))
                strName="-";
            else
                strName=jsonObj.getString("NAME");

            if(jsonObj.getString("PHONE").equals("null"))
                strPhone="-";
            else
                strPhone=jsonObj.getString("PHONE");

            if(jsonObj.getString("GRADE").equals("null"))
                strGrade="-";
            else
                strGrade=jsonObj.getString("GRADE");

            if(jsonObj.getString("WRITE_TIME").equals("null"))
                strWriteTime="-";
            else {
                strWriteTime = jsonObj.getString("WRITE_TIME");
                String temp[] = strWriteTime.split(" ");
                strWriteTime = temp[0] + "\n" + temp[1];
            }
            userList.add(new UserInfo(strID,strName,strPhone,strGrade,strWriteTime));
        }
        return jsonArray.length();
    }
    static public int getResultJson(String response) throws JSONException{
        JSONArray jsonArray = new JSONArray(response);
        JSONObject jsonObject = new JSONObject(jsonArray.getString(0));
        return Integer.parseInt(jsonObject.getString("RESULT_OK"));
    }
}
