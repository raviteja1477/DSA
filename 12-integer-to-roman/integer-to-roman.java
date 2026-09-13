class Solution {
    public String intToRoman(int num) {
        ArrayList<String> arr = new ArrayList<>();
        while(num>=1000){
            arr.add("M");
            num=num-1000;


        }
        if(num>=900){
            arr.add("CM");
            num=num-900;
        }
        while(num>=500){
            arr.add("D");
            num=num-500;
        }
        if(num>=400){
            arr.add("CD");
            num=num-400;
        }
        while(num>=100){
            arr.add("C");
            num=num-100;
        }
        if(num>=90){
            arr.add("XC");
            num=num-90;
        }
        while(num>=50){
            arr.add("L");
            num=num-50;
        }
        if(num>=40){
            arr.add("XL");
            num=num-40;
        }
        while(num>=10){
            arr.add("X");
            num=num-10;
        }
        if(num==9){
            arr.add("IX");
            num=num-9;
        }
        while(num>=5){
            arr.add("V");
            num=num-5;
        }
        if(num==4){
            arr.add("IV");
            num=num-4;
        }
        while(num>=1){
            arr.add("I");
            num=num-1;
        }
        return String.join("", arr);
        
        
    }
    
}