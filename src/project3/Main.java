package project3;

public class Main {
    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter(
                //shapes
                "x = [0,0,450,450]\n draw x #010101\n z=[60,60,130,130]\n fill z #f1d3f3\n" +
                        " z = [60,260,130,130]\n   fill z #2a6f1f\n x = [50,50,150,150]\n y = [150,150,75,75]\n"+
                        "z = x-y\n draw z #0000aa\n z=[260,60,130,130]\n fill z #7bd9ed\n x = [250,50,75,75]\n" +
                        "y = [300,100,100,100]\n z = x+y\n draw z #00a000\n x = [50,250,150,150]\n y = [75,250,100,100]\n" +
                        "z = x-y\n draw z #ffe400\n z=[260,260,130,130]\n fill z #ffe400\n x = [250,250,150,25]\n" +
                        "x = x + [250,300,150,25]\n x = x + [250,350,150,25]\n x = x + [250,250,25,50]\n" +
                        "x = x + [350,300,25,50]\nx = x + [250,350,25,50]\ndraw x #000000"

                // smiley
//                "x = [0,0,500,500]\n draw x #010101\n x = [100,100,50,50]\n fill x #000000\n" +
//                "x = [350,100,50,50]\n fill x #000000\n x = [350,300,50,50]\n fill x #000000\n" +
//                "x = [100,300,50,50]\n fill x #000000\n x = [150,350,200,50]\n fill x #000000\n" +
//                "x = [225,140,50,100]\n fill x #a00000"

                // squares
//                "x = [0,0,500,500]\n draw x #010101\n tl = [100,100,140,140]\n tr = [260,100,140,140]\n" +
//                "bl = [100,260,140,140]\n br = [260,260,140,140]\n fill tl #ff0000\n fill tr #00ff00\n" +
//                "fill bl #0000ff\n fill br #000000\n c = [220,220,60,60]\n c = c - (tl+tr+bl+br)\n" +
//                "fill c #a0a0a0"

//                "draw ([1,1,1,5] + [1,2,2,4] + [1,3,3,3] + [1,4,4,2] + [1,5,5,1]) #00ff00"
//                "draw ([1,1,5,5] + [3,0,1,7] + [0,3,7,1] + [3,3,1,1] + [0,0,1,1] + [6,0,1,1] + [0,6,1,1] + [6,6,1,1]) #00ff00"
//                    "fill (((([2,0,5,9]-([2,2,5,2]-[4,2,1,2]))-([4,0,1,9]&[4,7,1,1]+[4,4,1,1]))+([1,1,1,4]+[7,1,1,4])&[0,0,9,9])-(([0,7,9,1]&[6,0,1,9])+([0,7,9,1]&[2,0,1,9])))-([3,6,1,1]+[0,6,9,1]&[5,5,1,4]) #00ff00"

//
//        "x = [2,2,4,4]\nfill x #0000ff\n","#ffffff#ffffff#ffffff#ffffff#ffffff#ffffff\n#ffffff#ffffff#ffffff#ffffff#ffffff#ffffff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n"
//        {"xyz= [2,2,4,4]\nfill xyz #010203\n","#ffffff#ffffff#ffffff#ffffff#ffffff#ffffff\n#ffffff#ffffff#ffffff#ffffff#ffffff#ffffff\n#ffffff#ffffff#010203#010203#010203#010203\n#ffffff#ffffff#010203#010203#010203#010203\n#ffffff#ffffff#010203#010203#010203#010203\n#ffffff#ffffff#010203#010203#010203#010203\n"},
//        {"x =[2,2,4,4]\nfill x #0000ff\n","#ffffff#ffffff#ffffff#ffffff#ffffff#ffffff\n#ffffff#ffffff#ffffff#ffffff#ffffff#ffffff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n"},
//        {"fill [2,2,4,4] #0000ff\n","#ffffff#ffffff#ffffff#ffffff#ffffff#ffffff\n#ffffff#ffffff#ffffff#ffffff#ffffff#ffffff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n"},
//        {"x1=[2,2,4,4]\nfill x1 #0000ff\n","#ffffff#ffffff#ffffff#ffffff#ffffff#ffffff\n#ffffff#ffffff#ffffff#ffffff#ffffff#ffffff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n"},
//        {"x=[2 ,2,4,4]\nfill x #0000ff\n","#ffffff#ffffff#ffffff#ffffff#ffffff#ffffff\n#ffffff#ffffff#ffffff#ffffff#ffffff#ffffff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n"},
//        {"x=[2, 2,4,4]\nfill x #0000ff\n","#ffffff#ffffff#ffffff#ffffff#ffffff#ffffff\n#ffffff#ffffff#ffffff#ffffff#ffffff#ffffff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n"},
//        {"x=[2, 2, 4, 4]\nfill x #0000ff\n","#ffffff#ffffff#ffffff#ffffff#ffffff#ffffff\n#ffffff#ffffff#ffffff#ffffff#ffffff#ffffff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n#ffffff#ffffff#0000ff#0000ff#0000ff#0000ff\n"}


        );
//        System.out.println(interpreter.run().toString());

        interpreter.createCanvas().show();

    }

}
