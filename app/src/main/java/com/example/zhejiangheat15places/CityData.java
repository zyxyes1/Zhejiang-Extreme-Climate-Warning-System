package com.example.zhejiangheat15places;

import java.util.HashMap;
import java.util.Map;

public class CityData {
    // 城市列表
    public static final String[] CITIES = {
            "杭州市", "宁波市", "温州市", "嘉兴市", "湖州市",
            "绍兴市", "金华市", "衢州市", "舟山市", "台州市", "丽水市"
    };

    // 城市与区县映射
    public static final Map<String, String[]> CITY_DISTRICT_MAP = new HashMap<>();
    public static final Map<String, String> DISTRICT_CODE_MAP = new HashMap<>();

    static {
        initCityData();
    }

    private static void initCityData() {
        // 杭州市
        String[] hangzhouDistricts = {
                "上城区", "拱墅区", "西湖区", "滨江区", "萧山区",
                "余杭区", "临平区", "钱塘区", "富阳区", "临安区",
                "桐庐县", "淳安县", "建德市"
        };
        CITY_DISTRICT_MAP.put("杭州市", hangzhouDistricts);
        for (int i = 0; i < hangzhouDistricts.length; i++) {
            if (i == 11 || i == 12) {
                DISTRICT_CODE_MAP.put(hangzhouDistricts[i], "58543");
            } else {
                DISTRICT_CODE_MAP.put(hangzhouDistricts[i], "58457");
            }
        }

        // 宁波市
        String[] ningboDistricts = {
                "海曙区", "江北区", "北仑区", "镇海区", "鄞州区",
                "奉化区", "余姚市", "慈溪市", "宁海县", "象山县"
        };
        CITY_DISTRICT_MAP.put("宁波市", ningboDistricts);
        for (int i = 0; i < ningboDistricts.length; i++) {
            DISTRICT_CODE_MAP.put(ningboDistricts[i], "58562");
        }

        // 温州市
        String[] wenzhouDistricts = {
                "鹿城区", "龙湾区", "瓯海区", "洞头区", "永嘉县",
                "平阳县", "苍南县", "文成县", "泰顺县", "瑞安市",
                "乐清市", "龙港市"
        };
        CITY_DISTRICT_MAP.put("温州市", wenzhouDistricts);
        for (String district : wenzhouDistricts) {
            DISTRICT_CODE_MAP.put(district, "58752");
        }

        // 嘉兴市
        String[] jiaxingDistricts = {
                "南湖区", "秀洲区", "嘉善县", "海盐县", "平湖市",
                "海宁市", "桐乡市"
        };
        CITY_DISTRICT_MAP.put("嘉兴市", jiaxingDistricts);
        for (String district : jiaxingDistricts) {
            DISTRICT_CODE_MAP.put(district, "58464");
        }

        // 湖州市
        String[] huzhouDistricts = {
                "吴兴区", "南浔区", "德清县", "长兴县", "安吉县"
        };
        CITY_DISTRICT_MAP.put("湖州市", huzhouDistricts);
        for (String district : huzhouDistricts) {
            DISTRICT_CODE_MAP.put(district, null);
        }

        // 绍兴市
        String[] shaoxingDistricts = {
                "越城区", "柯桥区", "上虞区", "新昌县", "嵊州市",
                "诸暨市"
        };
        CITY_DISTRICT_MAP.put("绍兴市", shaoxingDistricts);
        for (String district : shaoxingDistricts) {
            DISTRICT_CODE_MAP.put(district, "58556");
        }

        // 金华市
        String[] jinhuaDistricts = {
                "婺城区", "金东区", "武义县", "浦江县", "磐安县",
                "兰溪市", "义乌市", "东阳市", "永康市"
        };
        CITY_DISTRICT_MAP.put("金华市", jinhuaDistricts);
        for (String district : jinhuaDistricts) {
            DISTRICT_CODE_MAP.put(district, "58549");
        }

        // 衢州市
        String[] quzhouDistricts = {
                "柯城区", "衢江区", "江山市", "龙游县", "常山县",
                "开化县"
        };
        CITY_DISTRICT_MAP.put("衢州市", quzhouDistricts);
        for (String district : quzhouDistricts) {
            DISTRICT_CODE_MAP.put(district, null);
        }

        // 舟山市
        String[] zhoushanDistricts = {
                "定海区", "普陀区", "岱山县", "嵊泗县"
        };
        CITY_DISTRICT_MAP.put("舟山市", zhoushanDistricts);
        for (int i = 0; i < zhoushanDistricts.length; i++) {
            if (i == 3) {
                DISTRICT_CODE_MAP.put(zhoushanDistricts[i], "58472");
            } else {
                DISTRICT_CODE_MAP.put(zhoushanDistricts[i], "58477");
            }
        }

        // 台州市
        String[] taizhouDistricts = {
                "椒江区", "黄岩区", "路桥区", "临海市", "温岭市",
                "玉环市", "天台县", "仙居县", "三门县"
        };
        CITY_DISTRICT_MAP.put("台州市", taizhouDistricts);
        for (int i = 0; i < taizhouDistricts.length; i++) {
            if (i == 5) {
                DISTRICT_CODE_MAP.put(taizhouDistricts[i], "58667");
            } else {
                DISTRICT_CODE_MAP.put(taizhouDistricts[i], "58665");
            }
        }

        // 丽水市
        String[] lishuiDistricts = {
                "莲都区", "青田县", "缙云县", "遂昌县", "松阳县",
                "云和县", "庆元县", "景宁县", "龙泉市"
        };
        CITY_DISTRICT_MAP.put("丽水市", lishuiDistricts);
        for (int i = 0; i < lishuiDistricts.length; i++) {
            if (i == 5 || i == 6 || i == 8) {
                DISTRICT_CODE_MAP.put(lishuiDistricts[i], "58647");
            } else {
                DISTRICT_CODE_MAP.put(lishuiDistricts[i], "58646");
            }
        }
    }
}