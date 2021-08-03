package chap07_Test;

public class StubAutoDebitInfoRepository implements AutoDebitInfoRepository{
    @Override
    public void save(AutoDebitInfo info) {

    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return null;
    }
}
