import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.tensquare.user.entity.Role;

public class Test {

    public static void main(String[] args) {
        dotDotDot(1,2,3,4,5);


    }

    private static void dotDotDot(int... num){
        DefaultIdentifierGenerator identifierGenerator = new DefaultIdentifierGenerator();

        for(int i=0;i<10;i++){
            Long aLong = identifierGenerator.nextId(Role.class);
            System.out.println(aLong);
        }


    }


}
