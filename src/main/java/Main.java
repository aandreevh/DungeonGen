import core.struct.Structure;
import core.struct.gen.CompositeStructureGenerator;
import core.struct.gen.BaseStructureGenerator;
import core.struct.gen.IStreamedStructureGenerator;
import core.sys.Clock;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import java.util.stream.Collectors;

public class Main {

    public static void getRenderData(int data[][],Structure s,int offX,int offY){
        if(s.isBase()){

            for(int yy=s.getAABB().getY();yy<s.getAABB().getY()+s.getAABB().getHeight();yy++)
                for(int xx=s.getAABB().getX();xx<s.getAABB().getX()+s.getAABB().getWidth();xx++)
                {
                    if(xx == s.getAABB().getX() || xx == s.getAABB().getX()+s.getAABB().getWidth()-1 ||
                            yy==s.getAABB().getY() ||yy==s.getAABB().getY()+s.getAABB().getHeight()-1){
                        data[yy-offY][xx-offX] = 1;
                    }else data[yy-offY][xx-offX] = 2;
                }
        }
        else for(Structure ss : s) getRenderData(data,ss,offX,offY);

    }


    public static int[][] getRenderData(Structure s){
        int[][] data= new int[s.getAABB().getHeight()][s.getAABB().getWidth()];

        getRenderData(data,s,s.getAABB().getX(),s.getAABB().getY());

        return data;
    }

    public static void draw(int[][] data){
        StringBuilder builder = new StringBuilder();
        for(int yy=0;yy< data.length;yy++) {
            for (int xx=0;xx<data[yy].length;xx++)
                switch (data[yy][xx]){
                    case 0:
                    builder.append(" ");
                    break;
                    case 1:
                        builder.append("x");
                        break;
                    case 2:
                        builder.append("-");
                        break;
                    case 3:
                        builder.append("P");
                        break;
                }
            builder.append('\n');
        }
        System.out.println(builder.toString());
    }


    public static void main(String... args) {

        Clock globTimer= new Clock();
        Clock timer = new Clock();

        globTimer.start();

        Random rand = new Random();
        IStreamedStructureGenerator generator =
                new BaseStructureGenerator(rand, 7,7,15,15);

        List<Structure> primeStructs = new LinkedList<>();

        for(int i=0;i<1;i++){
            CompositeStructureGenerator gen = new CompositeStructureGenerator(rand,
                    generator.stream().limit(100).collect(Collectors.toList()),(w,s)->{
                if(s.isBase()){
                    s.setBaseAABB(s.getAABB().shofiset(1,1,-2,-2));
                }
                w.attach(s);
            });

            timer.start();
            Structure primeStruct = gen.generate();
            System.out.println(primeStruct);
            primeStructs.add(primeStruct);
            timer.end();

        }

        CompositeStructureGenerator gen = new CompositeStructureGenerator(rand,primeStructs);

        timer.start();
        Structure thStruct = gen.generate();
        timer.end();

    /*    timer.start();
        draw(getRenderData(thStruct));
        timer.end();
*/
        System.out.println(thStruct);

        globTimer.end();
    }

}
