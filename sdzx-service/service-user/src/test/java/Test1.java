import com.sd.sdzx.model.entity.base.Region;
import com.sd.sdzx.user.mapper.RegionMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


public class Test1 {

    @Autowired
    private RegionMapper regionMapper;

    public void test() {
        List<Region> list = regionMapper.findByParent(110116);
        for (Region r:list) {
            System.out.println("r = " + r);
        }
    }

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        test1.test();
    }

}
