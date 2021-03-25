package com.yunfu.help.activity.found;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.yunfu.help.R;
import com.yunfu.help.adapter.found.SelectAddrAdapter;
import com.yunfu.help.base.BaseActivity;
import com.yunfu.help.utils.DialogUtil;
import com.yunfu.help.utils.GetLocation;

import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 选择区域
 */
public class SelectCityActivity extends BaseActivity implements OnGetPoiSearchResultListener {
    @BindView(R.id.et_key)
    EditText etKey;
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.listView)
    ListView listView;
    //定位信息
    private BaiduMap mBaiduMap;
    private PoiSearch mPoiSearch;
    private PoiCitySearchOption poiCitySearchOption;
    private LatLng latLng;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_city;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();

        /**
         * 开始定位
         */
        DialogUtil.showProgress(this,"定位中");
        GetLocation.getInstance().setLocation(this, location -> {
            DialogUtil.closeProgress();
            latLng=new LatLng(location.getLatitude(),location.getLongitude());
            //搜索位置
            initPosition(location.getCity());
        });


        /**
         * 监控输入框
         */
        etKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==0){
                    return;
                }
                poiCitySearchOption.keyword(s.toString());
                mPoiSearch.searchInCity(poiCitySearchOption);
            }
        });
    }

    @OnClick(R.id.lin_back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 搜索位置
     */
    private void initPosition(String city){
        mBaiduMap = mapView.getMap();
        //隐藏缩放按钮
        mapView.showZoomControls(false);
        //定位地图
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(latLng));
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.zoomTo(16f), 500);

        // POI初始化搜索模块，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);

        poiCitySearchOption=new PoiCitySearchOption();
        poiCitySearchOption.city(city);
        poiCitySearchOption.keyword("公司");
        poiCitySearchOption.cityLimit(false);
        poiCitySearchOption.pageCapacity(50);
        mPoiSearch.searchInCity(poiCitySearchOption);
    }


    /**
     * 获取位置列表
     * @param poiResult
     */
    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
            final List<PoiInfo> list = poiResult.getAllPoi();
            SelectAddrAdapter selectAddrAdapter=new SelectAddrAdapter(this,list);
            listView.setAdapter(selectAddrAdapter);
            listView.setOnItemClickListener((parent, view, position, id) -> {
                Intent intent=new Intent();
                intent.putExtra("PoiInfo",list.get(position));
                setResult(1000,intent);
                finish();
            });
        }
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPoiSearch.destroy();
    }
}
