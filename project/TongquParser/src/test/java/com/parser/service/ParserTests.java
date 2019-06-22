package com.parser.service;

import com.alibaba.fastjson.JSONArray;
import com.parser.serviceImpl.ParserImpl;
import com.parser.util.FileUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class ParserTests {

    private Parser parser;

    @Before
    public void init() {
        this.parser = new ParserImpl();
    }
    @Test
    public void parser1151FullTest() {
        String jsonContent = "[{\n" +
                "        \"actid\": 20455,\n" +
                "        \"name\": \"大师讲坛第128期——多元发色团和激发态——从离散组分到超分子组装、纳米结构和功能\",\n" +
                "        \"type\": 2,\n" +
                "        \"typename\": \"讲座\",\n" +
                "        \"location\": \"闵行校区光彪楼一楼报告厅\",\n" +
                "        \"source\": \"大师讲坛\",\n" +
                "        \"create_time\": \"2019-05-24 22:36:41\",\n" +
                "        \"sign_end_time\": \"2019-05-30 10:25\",\n" +
                "        \"sign_start_time\": \"2019-05-24 22:34\",\n" +
                "        \"start_time\": \"2019-05-30 10:30\",\n" +
                "        \"end_time\": \"2019-05-30 12:00\",\n" +
                "        \"member_count\": 19,\n" +
                "        \"max_member\": 0,\n" +
                "        \"time_status\": 1,\n" +
                "        \"time_status_str\": \"正在报名\",\n" +
                "        \"body_text\": \"1151项满分素拓，任咏华（Vivian Wing-Wah YAM），中国科学院院士、第三世界科学院院士、美国国家科学院外籍院士、欧洲人文和自然科学院外籍院士、香港科学院创院院士，现担任香港大学化学系讲座教授。1985年毕业于香港大学化学系，1988年获该校博士学位，之后进入香港城市大学任教；1990年转入香港大学任教，先后担任讲师、高级讲师、教授、讲座教授。2001年38岁时增选为中国科学院院士，是当时最年轻的院士；2005年获得中国国家自然科学奖二等奖；2006年当选第三世界科学院院士；2011年获得世界杰出女科学家成就奖；2012年当选为美国国家科学院外籍院士；2015年当选为欧洲人文和自然科学院外籍院士，荣获香港特别行政区铜紫荆星章和英国皇家化学学会Ludwig Mond Award。 任咏华院士是国际著名的无机化学家，主要从事配位和有机金属化学、超分子化学和光化学的基础研究工作。2005年，以“过渡金属炔基及硫属簇配合物的分子设计及其发光性能的研究”获得中国国家自然科学奖二等奖。已经发表学术论文500余篇，出版学术专著多部。担任美国化学会Inorg. Chem. 副主编、J. Am. Chem. Soc.、Acc. Chem. Res.、Chem. Rev.、ACS Nano、Chem. Mater.、ACS Mater. Lett.、英国皇家化学会Chem. Sci., Chem. Soc. Rev., New Journal of Chem.、Cell 出版社Chem、Elsevier 出版社Coord. Chem. Rev.、Inorg. Chim. Acta和Wiley 出版社Angew. Chem. 等国际杂志及中国化学会CCS Chemistry等杂志编委。\",\n" +
                "        \"ruledesc_text\": \"报名方式 以jaccount登陆“同去网”，点击“我要去”即可在线填写报名信息。 温馨提示 需要听课证明的同学请在讲座结束后依次领取。为了维持会场的秩序和表示对大师的尊重，我们不预留座位，请大家尽量提前到场，谢谢合作！ 新的通知 大师讲坛为听众提供纸质听课证明，将在每期讲座结束后统一发放。可用于素拓（以各院系规定为准）、青马党校等。请大家相互转告，您的支持是我们前进的最大动力。 大师讲坛学生组委会 2019年5月24日\"\n" +
                "    }]";
        JSONArray res = parser.parse(jsonContent);
        assertThat(res.toString(), containsString("\"Qnum\":\"1151\""));
        assertThat(res.toString(), containsString("\"Score\":100"));
    }
    @Test
    public void parser115195Test() {
        String jsonContent = "[{\n" +
                "        \"actid\": 20455,\n" +
                "        \"name\": \"大师讲坛第128期——多元发色团和激发态——从离散组分到超分子组装、纳米结构和功能\",\n" +
                "        \"type\": 2,\n" +
                "        \"typename\": \"讲座\",\n" +
                "        \"location\": \"闵行校区光彪楼一楼报告厅\",\n" +
                "        \"source\": \"大师讲坛\",\n" +
                "        \"create_time\": \"2019-05-24 22:36:41\",\n" +
                "        \"sign_end_time\": \"2019-05-30 10:25\",\n" +
                "        \"sign_start_time\": \"2019-05-24 22:34\",\n" +
                "        \"start_time\": \"2019-05-30 10:30\",\n" +
                "        \"end_time\": \"2019-05-30 12:00\",\n" +
                "        \"member_count\": 19,\n" +
                "        \"max_member\": 0,\n" +
                "        \"time_status\": 1,\n" +
                "        \"time_status_str\": \"正在报名\",\n" +
                "        \"body_text\": \"1151项95分素拓，任咏华（Vivian Wing-Wah YAM），中国科学院院士、第三世界科学院院士、美国国家科学院外籍院士、欧洲人文和自然科学院外籍院士、香港科学院创院院士，现担任香港大学化学系讲座教授。1985年毕业于香港大学化学系，1988年获该校博士学位，之后进入香港城市大学任教；1990年转入香港大学任教，先后担任讲师、高级讲师、教授、讲座教授。2001年38岁时增选为中国科学院院士，是当时最年轻的院士；2005年获得中国国家自然科学奖二等奖；2006年当选第三世界科学院院士；2011年获得世界杰出女科学家成就奖；2012年当选为美国国家科学院外籍院士；2015年当选为欧洲人文和自然科学院外籍院士，荣获香港特别行政区铜紫荆星章和英国皇家化学学会Ludwig Mond Award。 任咏华院士是国际著名的无机化学家，主要从事配位和有机金属化学、超分子化学和光化学的基础研究工作。2005年，以“过渡金属炔基及硫属簇配合物的分子设计及其发光性能的研究”获得中国国家自然科学奖二等奖。已经发表学术论文500余篇，出版学术专著多部。担任美国化学会Inorg. Chem. 副主编、J. Am. Chem. Soc.、Acc. Chem. Res.、Chem. Rev.、ACS Nano、Chem. Mater.、ACS Mater. Lett.、英国皇家化学会Chem. Sci., Chem. Soc. Rev., New Journal of Chem.、Cell 出版社Chem、Elsevier 出版社Coord. Chem. Rev.、Inorg. Chim. Acta和Wiley 出版社Angew. Chem. 等国际杂志及中国化学会CCS Chemistry等杂志编委。\",\n" +
                "        \"ruledesc_text\": \"报名方式 以jaccount登陆“同去网”，点击“我要去”即可在线填写报名信息。 温馨提示 需要听课证明的同学请在讲座结束后依次领取。为了维持会场的秩序和表示对大师的尊重，我们不预留座位，请大家尽量提前到场，谢谢合作！ 新的通知 大师讲坛为听众提供纸质听课证明，将在每期讲座结束后统一发放。可用于素拓（以各院系规定为准）、青马党校等。请大家相互转告，您的支持是我们前进的最大动力。 大师讲坛学生组委会 2019年5月24日\"\n" +
                "    }]";
        JSONArray res = parser.parse(jsonContent);
        assertThat(res.toString(), containsString("\"Qnum\":\"1151\""));
        assertThat(res.toString(), containsString("\"Score\":95"));
    }
}
