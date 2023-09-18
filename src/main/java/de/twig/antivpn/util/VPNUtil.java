package de.twig.antivpn.util;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

public class VPNUtil {

    public boolean checkConnection(String ip) throws IOException {
        String key = new ConfigUtil().getAPIKey();
        URL url = new URL("https://ipqualityscore.com/api/json/ip/" + key + "/" + ip + "?strictness=2&fast=1");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();
        for (String line : reader.lines().collect(Collectors.toList())) {
            sb.append(line);
        }
        String json = sb.toString();
        JSONObject object = new JSONObject(json);
        boolean vpn = object.getBoolean("vpn");
        boolean vpn_active = object.getBoolean("active_vpn");
        boolean tor = object.getBoolean("tor");
        boolean tor_active = object.getBoolean("active_tor");
        boolean abuse = object.getBoolean("recent_abuse");
        String abuse_level = object.getString("abuse_velocity");

        boolean deny = vpn;

        if(vpn_active) deny = true;
        if(tor) deny = true;
        if(tor_active) deny = true;
        if(abuse) {
            if(!abuse_level.equals("medium") && !abuse_level.equals("low")) {
                deny = true;
            }
        }
        return deny;
    }

}
