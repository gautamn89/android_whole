package com.example.webcall;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;

import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;
import android.util.Log;

public class RecentList extends CordovaPlugin {
     public static final String ACTION_GET_RECENT_LIST = "getRecentList";
     
     public boolean execute(String actionName, JSONArray arguments, CallbackContext callbackContext) throws JSONException {
     
    	 JSONObject result = new JSONObject();
    	 JSONArray resultarray = new JSONArray();
         
    	 
    	 try {
    		 
    		 if(ACTION_GET_RECENT_LIST.equals(actionName)){
    			 result = getAllCallLog();
    			 //resultarray.put("OK");
    			 //result.put("result", resultarray);
    			 
    			 //callbackContext.sendPluginResult(new PluginResult(Status.OK, result));
    			 callbackContext.success(result);
    			 return true;
    			 
    		 } else {
    		 //callbackContext.sendPluginResult(new PluginResult(Status.INVALID_ACTION));
    			 callbackContext.error("INVALID_ACTION");
    			 return false;
    		 }
         } catch (JSONException jsonEx) {
        	 //callbackContext.sendPluginResult(new PluginResult(Status.JSON_EXCEPTION));
        	 callbackContext.error("JSON_EXCEPTION");
        	 return false;
         }
     
     }
     
     
    /** @Override
     public JSONObject execute(String actionName, JSONArray arguments, CallbackContext callbackContext) throws JSONException {
      
    	 JSONObject callLogs = new JSONObject();
         //PluginResult result = null;


         /** // try {
        	 callLogs = getAllCallLog();
             result = new PluginResult(Status.OK, callLogs);
                 switch (getActionItem(actionName))
                 {
                         case 1:
                                 callLogs = getAllCallLog(arguments);
                                 result = new PluginResult(Status.OK, callLogs);
                                 break;
                         default:
                                 result = new PluginResult(Status.INVALID_ACTION);
                 }
         } catch (JSONException jsonEx) {
                 result = new PluginResult(Status.JSON_EXCEPTION);
         }
         **/
         
      /**   try {
        if(ACTION_GET_RECENT_LIST.equals(actionName)){
         callLogs = getAllCallLog();
         callLogs.put("Result", "OK");
         return callLogs;
        }
        callLogs.put("Result", "Invalid Action");
        return callLogs;
         } catch (JSONException jsonEx) {
             //result = new PluginResult(Status.JSON_EXCEPTION);
        	 callLogs.put("Result", "Exception");;
             return callLogs;
     }
         callLogs.put("Result", null);
         return callLogs;
 }**/


 //private JSONObject getAllCallLog(JSONArray requirements) throws JSONException
     private JSONObject getAllCallLog() throws JSONException
     {
         JSONObject callLog = new JSONObject();
         //String mSelectionClause = android.provider.CallLog.Calls.DATE+ " >= ?";
         
         Log.i("getAllCallLog","Inside it");
         String[] strFields = {
                 //android.provider.CallLog.Calls.DATE,
                 android.provider.CallLog.Calls.NUMBER, 
                 //android.provider.CallLog.Calls.TYPE,
                 //android.provider.CallLog.Calls.DURATION,
                 //android.provider.CallLog.Calls.NEW,
                 android.provider.CallLog.Calls.CACHED_NAME
                 //android.provider.CallLog.Calls.CACHED_NUMBER_TYPE,
                 //android.provider.CallLog.Calls.CACHED_NUMBER_LABEL//,
         };

         try {
                 Cursor callLogCursor = cordova.getActivity().getContentResolver().query(
                         android.provider.CallLog.Calls.CONTENT_URI,
                         strFields,
                         null,
                         null,
                         android.provider.CallLog.Calls.DEFAULT_SORT_ORDER  + " LIMIT 30"
                     );

                 Log.i("getAllCallLog","After Cursor");
         int callCount = callLogCursor.getCount();
         Log.i("getAllCallLog","Call logsCount");
         if(callCount>0){
                 JSONArray callLogItem = new JSONArray();
                 JSONArray callLogItems = new JSONArray();

                 String[] columnNames = callLogCursor.getColumnNames();

                 callLogCursor.moveToFirst();
                 do
                 {
                	 Log.i("getAllCallLog","populating with munbers");
                	 	 callLogItem.put(callLogCursor.getString(0));
                         callLogItem.put(callLogCursor.getString(1));
                         //callLogItem.put(callLogCursor.getInt(2));
                         //callLogItem.put(callLogCursor.getLong(3));
                         //callLogItem.put(callLogCursor.getInt(4));
                         //callLogItem.put(callLogCursor.getString(5));
                         //callLogItem.put(callLogCursor.getInt(6));
                         callLogItems.put(callLogItem);
                         callLogItem = new JSONArray();

                 }while(callLogCursor.moveToNext());

                 callLog.put("Rows", callLogItems);
         }
         callLogCursor.close();
         }catch(Exception e)
         {

                 Log.d("CallLog_Plugin", " ERROR : SQL to get cursor: ERROR " + e.getMessage());
                 Log.i("CallLog_Plugin", " ERROR : SQL to get cursor: ERROR " + e.getMessage());
                 
         }



         return callLog;
 }

 /**private JSONObject getTimeRangeCallLog(JSONArray requirements)
 {

 private int getActionItem(String actionName) throws JSONException 
 {
         JSONObject actions = new JSONObject("{'all':1,'last':2,'time':3}");
         if (actions.has(actionName))
                 return actions.getInt(actionName);

         return 0;
    	 
    	 
 }
 }***/
}
