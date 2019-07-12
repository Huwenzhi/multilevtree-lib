package com.hu_sir.mylibs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.widget.ListView;

import com.google.gson.Gson;
import com.hu_sir.multilevtree.bean.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String json = "{\"msg\":\"查询成功!\",\"success\":true,\"backLists\":[{\"name\":null,\"pid\":\"1\",\"id\":null},{\"name\":\"段浩然\",\"pid\":\"1\",\"id\":635},{\"name\":\"谷丰源粮油有限公司\",\"pid\":635,\"id\":17181},{\"name\":\"陇西李莉\",\"pid\":635,\"id\":16057},{\"name\":\"定西市安定区盛泰粮油交易中心\",\"pid\":635,\"id\":18198},{\"name\":\"宁夏海原县张斌\",\"pid\":635,\"id\":18201},{\"name\":\"庆阳鹏源商贸有限公司\",\"pid\":635,\"id\":13589},{\"name\":\"宁夏回族自治区固原市冶富祥\",\"pid\":635,\"id\":15485},{\"name\":\"石嘴山李彦军\",\"pid\":635,\"id\":16296},{\"name\":\"银川杨锦东\",\"pid\":635,\"id\":18803},{\"name\":\"大武口区付海霞\",\"pid\":635,\"id\":15655},{\"name\":\"吴忠市红寺堡区张锦荣\",\"pid\":635,\"id\":18971},{\"name\":\"静宁县杨刚龙\",\"pid\":635,\"id\":17457},{\"name\":\"盐池贾军（和）\",\"pid\":635,\"id\":16258},{\"name\":\"吴忠市利通区恒业粮行\",\"pid\":635,\"id\":15011},{\"name\":\"临洮曙光杨德义\",\"pid\":635,\"id\":16447},{\"name\":\"宁夏市西吉县丁荣\",\"pid\":635,\"id\":12984},{\"name\":\"银川市邱海涛\",\"pid\":635,\"id\":19447},{\"name\":\"临洮县娟娟粮油放心店\",\"pid\":635,\"id\":16520},{\"name\":\"银川新华百货连锁超市有限公司\",\"pid\":635,\"id\":14574},{\"name\":\"定西市谢建军\",\"pid\":635,\"id\":19401},{\"name\":\"庄浪县葛银川\",\"pid\":635,\"id\":20972},{\"name\":\"甘肃鸿远鑫通商贸有限公司\",\"pid\":635,\"id\":18571},{\"name\":\"环县缪崇东\",\"pid\":635,\"id\":20696},{\"name\":\"镇原县徐小广\",\"pid\":635,\"id\":20234},{\"name\":\"临洮县益多多商店\",\"pid\":635,\"id\":20247},{\"name\":\"庆阳荣源商贸有限公司\",\"pid\":635,\"id\":72151},{\"name\":\"陇西县文峰兴盛经销部\",\"pid\":635,\"id\":72152},{\"name\":\"甘肃省兰州市阎玉明\",\"pid\":635,\"id\":21243},{\"name\":\"张立静\",\"pid\":\"1\",\"id\":330},{\"name\":\"金昌市杨立荣\",\"pid\":330,\"id\":14667},{\"name\":\"张掖市金鹏商贸有限责任公司\",\"pid\":330,\"id\":14801},{\"name\":\"兰州海斌利\",\"pid\":330,\"id\":14806},{\"name\":\"盐池县夏国锦\",\"pid\":330,\"id\":18020},{\"name\":\"渭源县苏明祥\",\"pid\":330,\"id\":19783},{\"name\":\"甘肃金诚宏盛商贸有限公司\",\"pid\":330,\"id\":19589},{\"name\":\"乌海市嘉得商贸有限责任公司\",\"pid\":330,\"id\":18926},{\"name\":\"华亭柳丁丁\",\"pid\":330,\"id\":17938},{\"name\":\"嘉峪关凯悦兴利商贸有限公司\",\"pid\":330,\"id\":14478},{\"name\":\"银川市锦夏源植物油有限公司\",\"pid\":330,\"id\":19969},{\"name\":\"嘉峪关市天润万和副食批发部\",\"pid\":330,\"id\":17454},{\"name\":\"兰州维刚商贸有限责任公司\",\"pid\":330,\"id\":17467},{\"name\":\"华亭县永旺副食批发部\",\"pid\":330,\"id\":16275},{\"name\":\"武山县宋宏斌\",\"pid\":330,\"id\":19530},{\"name\":\"酒泉市浩峰商贸有限公司\",\"pid\":330,\"id\":14342},{\"name\":\"内蒙古乌海市韵冉商贸有限责任公司\",\"pid\":330,\"id\":20925},{\"name\":\"彭阳县马军\",\"pid\":330,\"id\":14922},{\"name\":\"漳县王永泉\",\"pid\":330,\"id\":20969},{\"name\":\"陇南市武都区伍强\",\"pid\":330,\"id\":21323},{\"name\":\"庆城县万家同康粮油水产商行\",\"pid\":330,\"id\":18386},{\"name\":\"张掖市甘宇商贸有限责任公司\",\"pid\":330,\"id\":20763},{\"name\":\"乌海苏三粮油\",\"pid\":330,\"id\":20187},{\"name\":\"中卫市中盛糕点坊鼓楼北街店\",\"pid\":330,\"id\":72146},{\"name\":\"华池县闫静\",\"pid\":330,\"id\":21225},{\"name\":\"天水王敏商贸行\",\"pid\":330,\"id\":20583},{\"name\":\"鑫福强粮行\",\"pid\":330,\"id\":20615}]}";
    private SearchView mMySearch;
    private ListView mMyListview;
    MyTreeAdapter myTreeAdapter;
    private List<Node> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initAdapter();
        initEvent();
    }

    private void initAdapter() {
        mDatas = new ArrayList<Node>();
        BackBean backBean=new Gson().fromJson(json,BackBean.class);
        sendDatas(backBean.getBackLists());
        myTreeAdapter=new MyTreeAdapter(mMyListview,this,mDatas,3,R.mipmap.icon_open,R.mipmap.icon_close);
        mMyListview.setAdapter(myTreeAdapter);

    }

    private void sendDatas(List<BackBean.BackListsBean> backLists) {
        for (BackBean.BackListsBean bean : backLists) {
            mDatas.add(new Node((TextUtils.isEmpty(bean.getId())?"1":bean.getId()), (TextUtils.isEmpty(bean.getPid()) ? "-1" : bean.getPid()),
                    (TextUtils.isEmpty(bean.getName())?"请选择经销商":bean.getName()), bean));
        }
    }

    private void initEvent() {
        mMySearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                myTreeAdapter.filterShowOther(s);
                return false;
            }
        });
    }

    private void initView() {
        mMySearch = (SearchView) findViewById(R.id.my_search);
        mMyListview = (ListView) findViewById(R.id.my_listview);

    }
}
