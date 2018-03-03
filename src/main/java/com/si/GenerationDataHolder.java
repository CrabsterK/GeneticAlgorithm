package com.si;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class GenerationDataHolder{
    private PrintWriter pw;
    private StringBuilder sb;// = new StringBuilder();
    private boolean generated;
    String columnNamesList = "id,Best,Worst,Average";

    public GenerationDataHolder() throws FileNotFoundException {
        this.pw = new PrintWriter(new File("output/GenerationsData.csv"));
        this.sb = new StringBuilder();
        generated = false;
        initializeStringBuilder();
    }

    public void addPopulationData(int id, int best, int worst, int avg){
        sb.append(id);
        sb.append(',');
        sb.append(best);
        sb.append(',');
        sb.append(worst);
        sb.append(',');
        sb.append(avg);
        sb.append('\n');
    }

    private void initializeStringBuilder(){
        sb.append(columnNamesList + "\n");
    }

    public boolean generateFile(){
        generated = false;
        pw.write(sb.toString());
        pw.close();
        generated = true;
        return generated;
    }
}
