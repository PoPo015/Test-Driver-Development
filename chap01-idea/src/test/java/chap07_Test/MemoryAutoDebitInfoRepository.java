package chap07_Test;

import java.util.HashMap;
import java.util.Map;

public class MemoryAutoDebitInfoRepository implements AutoDebitInfoRepository {
    // DB대신 맵을 이용해서 자동이체 정보 저장 (DB와 같은 영속성은 제공하지 않지만, 테스트에 사용할수 있을만큼 기능제공.)
    private Map<String, AutoDebitInfo> infos = new HashMap<>();

    @Override
    public void save(AutoDebitInfo info) {
        infos.put(info.getUserId(), info);
    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return infos.get(userId);
    }

}
