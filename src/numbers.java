import java.util.*;

public class numbers {
    private ArrayList<String> nums = new ArrayList<String>();

    private ArrayList<Double> group = new ArrayList<Double>();
    private ArrayList<Double> freq = new ArrayList<Double>();
    private ArrayList<Double> fmid = new ArrayList<Double>();
    private ArrayList<Double> cmFrequency = new ArrayList<Double>();
    private double Variance;
    private double Mean;

    public double getMean() {
        return Mean;
    }

    public void setMean(double mean) {
        Mean = mean;
    }

    public ArrayList<Double> getGroup() {
        return group;
    }

    public void setGroup() {
        ArrayList<String> nums2 = getNums();
        for(String line : nums2){
            int index = line.indexOf('<');
            int num1 = Integer.parseInt(line.substring(0,index));
            group.add((Double.parseDouble(line.substring(index+1)) + num1)/2);
        }
        this.group = group;
    }

    public double getVariance() {
        return Variance;
    }

    public void setVariance(double variance) {
        Variance = variance;
    }

    public String getCmFrequency()
    {
        setFmid();
        return "Cumulative frequency: "+cmFrequency+"\nMidpoints: "+group+"\nFmid: "+fmid+"\ngrouped data: "+nums;
    }

    public void setCmFrequency() {
        double placeHolder = 0;
        for (double val:
             freq) {
            placeHolder += val;
            cmFrequency.add(placeHolder);
        }
        this.cmFrequency = cmFrequency;
    }

    public void getFmid() {
        for (double val:
             fmid) {
            System.out.println(val);
        }
    }

    public void setFmid() {
        int x = 0;
        float sum = 0;
        for (double num:
                group) {
            fmid.add(num * freq.get(x));
            x++;
        }
        this.fmid = fmid;
    }

    public ArrayList<Double> getFreq() {
        return freq;
    }

    public void setFreq(double num) {
        freq.add(num);
        this.freq = freq;
    }

    public ArrayList<String> getNums() {
        return nums;
    }

    public void setNums(String line) {
        int index = line.indexOf(',');
        nums.add(line.replace(',','<'));
        this.nums = nums;
    }

    public double calcGMean(){
        double mean = 0, sum = 0;
        int x = 0;
        for (double val:
             fmid) {
            mean += val;
            sum += freq.get(x);
            x++;
        }
        setMean(mean/sum);
        return mean/sum;
    }

    public double calcMean(String grouped){
        double tempMean = 0, mean = 0;

        if(grouped.equals("g")){
            double sum = 0;
            int x = 0;
            for (double val:
                    fmid) {
                mean += val;
                sum += freq.get(x);
                x++;
            }
            setMean(mean/sum);
            tempMean = mean/sum;
        }
        else if (grouped.equals("u")){
            for (double val:
                    freq) {
                mean += val;
            }
            setMean(mean/freq.size());
            tempMean = mean/freq.size();
        }
        else{
            System.out.println("Incorrect input :P");
        }
        System.out.println(" ");
        return tempMean;
    }

    public void calcVariance(String grouped){
        double temp = 0;

        if(grouped.equals("u")){

            for (double val:
                    freq) {
                temp += Math.pow((val - calcMean("g")),2);
            }

            setVariance(temp/(freq.size()-1));
            System.out.println("The variance of your data is: "+temp/(freq.size()-1));
        }
        else if (grouped.equals("g")) {

            int x = 0;
            setCmFrequency();
            for (double val:
                    freq) {
                temp += Math.pow(group.get(x), 2)*val;
                x++;
            }
            temp -= cmFrequency.get(cmFrequency.size()-1)*Math.pow(calcGMean(),2);

            setVariance(temp/(cmFrequency.get(cmFrequency.size()-1)-1));
            System.out.println("The variance of your data is: "+temp/(cmFrequency.get(cmFrequency.size()-1)-1));
        }
        else {
            System.out.println("\nincorrect value");
        }



    }

    public void getSorted(){
       // double a = 0, b = 0, c= 0, x = 0, y = 0, z = 0;

        double a = 90/150;
        double b = 89/149;
        double c = 88/148;

        double x = 60/150;
        double y = 59/149;
        double z = 58/148;

        double answer = a+b+c * x+y+z;

        System.out.println(answer);
    }

    public void calcmedian(){
        int middle = 0;
        double median = 0;
        ArrayList<Double> freq2 = freq;
        Collections.sort(freq2);

        if(freq.size() % 2 == 0){
            middle = (freq.size()/2) -1;
            median = freq2.get(middle);
        }
        else {
            middle = (int)truncateNum(freq.size()/2) -1;
            median = (freq2.get(middle+1) - freq2.get(middle)/2);
        }
        System.out.println("Your median is: "+median);
    }

    public double calcQ1(int q, String grouped){
        double finalMean = 0;

        if(grouped.equals("g")){
            double n = cmFrequency.get(cmFrequency.size()-1);
            double pos = (n*q)/100;
            double fc = 0, div = 0, width = 0, O1 = 0;
            int interval = 0;
            for(int i = 1; i < cmFrequency.size(); i++){
                try{
                    if((cmFrequency.get(i-1) <= pos)&&(cmFrequency.get(i) >= pos)){
                        fc = cmFrequency.get(i-1);
                        div = freq.get(i);
                        int index = nums.get(i).indexOf('<');
                        width = Double.parseDouble(nums.get(i).substring(index+1)) - Double.parseDouble(nums.get(i).substring(0,index));
                        O1 = Double.parseDouble(nums.get(i).substring(0,index));
                    }
                }
                catch (IndexOutOfBoundsException e){
                    System.out.println("something went wrong");
                }
                finally {

                }
            }
            System.out.println("Position: "+pos+"\nlower limit: "+O1+"\nwidth: "+width+"\nSample size: "+n+"\nFrequency of chosen interval: "+div+"\nCumulative frequency before: "+fc+"\n");
            double s2 = pos - fc;
            double s3 = s2/div;
            double s4 = s3 * width;
            double s5 = s4+O1;
            finalMean = s5;
        }
        else if (grouped.equals("u")){
            ArrayList<Double> sample = freq;
            Collections.sort(sample);
            double pos = q*(sample.size()+1);
            pos = (pos/100)-1;
            double estVal = sample.get((int)truncateNum(pos));
            double deciVal = pos - truncateNum(pos);
            finalMean = sample.get((int)truncateNum(pos)) + (deciVal * (sample.get((int)truncateNum(pos+1)) - sample.get((int)truncateNum(pos))));
        }
        else{
            System.out.println("Incorrect input :P");
        }


        return finalMean;
    }

    public double truncateNum(double num){
        num*= Math.pow(10,0);
        num = Math.floor(num);
        num = num/Math.pow(10,0);

        return num;
    }

    public void clearList(String list){
        if(list.equals("f")){
            freq.clear();
        }
        else if(list.equals("m")){
            fmid.clear();
        }
        else if (list.equals("c")) {
            cmFrequency.clear();
        }
        else if(list.equals("n")){
            nums.clear();
        }
        else if(list.equals("g")){
            group.clear();
        }
        else if(list.equals("a")){
            nums.clear();
            cmFrequency.clear();
            fmid.clear();
            freq.clear();
            group.clear();
        }
        else {
            System.out.println("incorrect input");
        }
    }

    public void findMode(){
        int count = 0;
        ArrayList<Double> inst = freq;
        Collections.sort(inst);
        for (double val:
             freq) {
            System.out.println(inst);
        }

    }

}
