package me.terramain.teleporttableplugin.teleportTable;

import java.io.*;

public class Config {
    public static String teleportTablePluginFailsCatalog = "plugins"+ File.separatorChar+"teleportTable"+File.separatorChar;
    public static String teleportTableDataFailPath = teleportTablePluginFailsCatalog + "teleportTables.data";

    public static void saveVirusBlocks() throws IOException {
        File dir = new File(teleportTablePluginFailsCatalog);
        if (!dir.exists()) dir.mkdir();

        File file = new File(teleportTableDataFailPath);
        if (file.exists()) file.delete();
        file.createNewFile();

        BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter(file) );

        for (TeleportTable table : TableLogic.teleportTableList) {
            bufferedWriter.write(table.toString());
            bufferedWriter.newLine();
        }

        bufferedWriter.flush();
        bufferedWriter.close();
    }
    public static void loadVirusBlocks() throws IOException {
        File file = new File(teleportTableDataFailPath);
        if (!file.exists()) return;
        BufferedReader bufferedReader = new BufferedReader( new FileReader(file) );


        String line = "";
        while ( ( line=bufferedReader.readLine() ) != null) {
            if (!line.equals("")) TableLogic.teleportTableList.add(new TeleportTable(line));
        }
        bufferedReader.close();
    }
}
