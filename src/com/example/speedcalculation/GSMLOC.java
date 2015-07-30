package com.example.speedcalculation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import android.R.string;
import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GSMLOC extends Activity {


@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.gsmlocation);
displayTelephonyInfo();
}



private void setTextViewText(int id, String text) {
((TextView) findViewById(id)).setText(text);
}


private void displayTelephonyInfo() {
TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
GsmCellLocation loc = (GsmCellLocation) tm.getCellLocation();
// Reorganize it to do one getSomeData, logString it in pairs, with code guards
String mcc="", mnc="", lat="", lon="";

if (loc == null) {
return;
}
int cellid = loc.getCid();
int lac = loc.getLac();

try{
mcc = tm.getNetworkOperator().substring(0, 3);
mnc = tm.getNetworkOperator().substring(3, tm.getNetworkOperator().length()-1);
}catch (Exception e) {
	// TODO: handle exception
}

setTextViewText(R.id.TxtLac, String.valueOf(lac));
setTextViewText(R.id.TxtDID, String.valueOf(cellid));
setTextViewText(R.id.TxtMnc, mnc);
setTextViewText(R.id.TxtMcc, mcc);

try{

lat = getlati(ReadHTML("http://www.location-api.com/cell/get/?key=ozo8yffztl2eaq9cppdz&mcc="+mcc+"&mnc="+mnc+"&cellid="+cellid+"&lac="+lac));
lon =  getlong(ReadHTML("http://www.location-api.com/cell/get/?key=ozo8yffztl2eaq9cppdz&mcc="+mcc+"&mnc="+mnc+"&cellid="+cellid+"&lac="+lac));
}catch (Exception e) {
	// TODO: handle exception
}

setTextViewText(R.id.TxtLat, String.valueOf(lat));
setTextViewText(R.id.TxtLong, String.valueOf(lon));


}

private String getlati(String Val ) {
	// TODO Auto-generated method stub	
	String[] Values = Val.split("@");
	Values = Values[2].split(" ");
	return Values[6].substring(Values[6].indexOf("\"")+1 , Values[6].length()-1);
}

private String getlong(String Val) {
	// TODO Auto-generated method stub
	String[] Values = Val.split("@");
	Values = Values[2].split(" ");
	
	return Values[7].substring(Values[7].indexOf("\"")+1 , Values[7].length()-1);

}

static String ReadHTML(String url )
{
	String HTMLContent = "";
    try
    {
        URL oracle = new URL(url);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"UTF-8"));
        String inputLine;
        
        while ((inputLine = in.readLine()) != null)
            HTMLContent += inputLine+"@";
        in.close();             
    }
    catch(Exception ex)
    {
        
    }
    return HTMLContent;
}

}