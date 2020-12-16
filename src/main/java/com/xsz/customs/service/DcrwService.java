package com.xsz.customs.service;

import com.xsz.customs.mapper.dcDcrwExtMapper;
import com.xsz.customs.mapper.dcDcrwMapper;
import com.xsz.customs.mapper.dcUserExtMapper;
import com.xsz.customs.mapper.dcUserMapper;
import com.xsz.customs.model.dcDcrw;
import com.xsz.customs.model.dcDcrwExample;
import com.xsz.customs.model.dcUser;
import com.xsz.customs.model.dcUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DcrwService {
    @Autowired
    private dcDcrwMapper dcrwMapper;

    @Autowired
    private dcDcrwExtMapper dcrwExtMapper;

    @Autowired
    private dcUserMapper userMapper;

    @Autowired
    private dcUserExtMapper userExtMapper;

    //为每一个关区生成三个调查任务
    public boolean createAll(dcDcrw dcrw){
        dcUserExample userExample = new dcUserExample();
        dcDcrwExample dcrwExample = new dcDcrwExample();
        dcrwExample.createCriteria()
                .andDcRenwumcEqualTo(dcrw.getDcRenwumc());
        List<dcDcrw> dcrws = dcrwMapper.selectByExample(dcrwExample);
        //说明这个调查任务的名字还没有被使用过
        if (dcrws.size() == 0){
            //查找所有非海关科技司的用户
            userExample.createCriteria()
                    .andDcGqdmNotEqualTo("000001");
            List<dcUser> users = userMapper.selectByExample(userExample);
            dcDcrw dcdcrw = new dcDcrw();
            dcdcrw.setDcRenwuxh(dcrwExtMapper.SelectTheLatestMission()+1);
            for (dcUser user : users) {
                for (int i = 0; i<3; i++){
                    dcdcrw.setDcRenwumc(dcrw.getDcRenwumc());
                    dcdcrw.setDcRenwugqdm(user.getDcGqdm());
                    dcdcrw.setDcRenwugqname(user.getDcGqname());
                    if (i == 0){
                        dcdcrw.setDcDcbname("卫生检疫");
                    }
                    else if (i == 1){
                        dcdcrw.setDcDcbname("动物检疫");
                    }
                    else if(i == 2){
                        dcdcrw.setDcDcbname("植物检疫");
                    }
                    dcrwMapper.insert(dcdcrw);
                }
            }
            return true;
        }
       return false;
    }

    //单独为一个关区生成调查任务
    public boolean createSingle(dcDcrw dcrw){
        int flag = dcrwMapper.insert(dcrw);
        if (flag != 0){
            return true;
        }else
            return false;
    }

    //当为二级海关生成单独的任务时，还要为其名下的三级海关生成任务
    public boolean createSingleForSub(dcDcrw dcrw,List<dcUser> users){
        int flag = 1;
        String dcRenwumc = dcrw.getDcRenwumc();
        String dcdcbname = dcrw.getDcDcbname();
        dcDcrw dcrw2 = new dcDcrw();
        dcrw2.setDcRenwuxh(dcrw.getDcRenwuxh());
        for (dcUser user : users) {
            dcrw2.setDcRenwumc(dcRenwumc);
            dcrw2.setDcDcbname(dcdcbname);
            dcrw2.setDcRenwugqdm(user.getDcGqdm());
            dcrw2.setDcRenwugqname(user.getDcGqname());
            /*System.out.println(dcrw2.getDcRenwugqname());*/
            flag = dcrwMapper.insert(dcrw2);
        }
        if (flag != 0){
            return true;
        }else
            return false;
    }

    public int findLatestMission(){
        int rwxh = dcrwExtMapper.SelectTheLatestMission();
        return rwxh;
    }

    //这个方法是用来获取某个等级的所有调查任务的
    public List<dcDcrw> getLatestMissionList(int rwxh){
        dcDcrwExample dcrwExample = new dcDcrwExample();
        dcrwExample.createCriteria()
                .andDcRenwuxhEqualTo(rwxh);
        List<dcDcrw> dcrws = dcrwMapper.selectByExample(dcrwExample);
        return dcrws;
    }

    //这个方法是用来获取非某一等级的所有调查任务的
    public List<dcDcrw> getHistoryMissionList(int rwxh){
        dcDcrwExample dcrwExample = new dcDcrwExample();
        dcrwExample.createCriteria()
                .andDcRenwuxhNotEqualTo(rwxh);
        List<dcDcrw> dcrws = dcrwMapper.selectByExample(dcrwExample);
        return dcrws;
    }

    //这个方法是用来给调查任务列表分页的
    public List<dcDcrw> getLatestMissionListPage(List<dcDcrw> dcrws,int page,int rows,int size){
        int first = (page-1)*rows;
        int last = (page-1)*rows + rows;
        if (last > size){
            last = size;
        }
        return dcrws.subList(first,last);
    }

    public List<dcDcrw> findDcrwByGqdm(String gqdm,int rwxh){
        dcDcrwExample dcrwExample = new dcDcrwExample();
        dcrwExample.createCriteria()
                .andDcRenwugqdmEqualTo(gqdm)
                .andDcRenwuxhEqualTo(rwxh);
        List<dcDcrw> dcrws = dcrwMapper.selectByExample(dcrwExample);
        return dcrws;
    }
    public List<dcDcrw> findHistoryDcrwByGqdm(String gqdm,int rwxh){
        dcDcrwExample dcrwExample = new dcDcrwExample();
        dcrwExample.createCriteria()
                .andDcRenwugqdmEqualTo(gqdm)
                .andDcRenwuxhNotEqualTo(rwxh);
        List<dcDcrw> dcrws = dcrwMapper.selectByExample(dcrwExample);
        return dcrws;
    }


    public boolean deleteDcrwByRwmc(String rwmc){
        dcDcrwExample dcrwExample = new dcDcrwExample();
        dcrwExample.createCriteria()
                .andDcRenwumcEqualTo(rwmc);
        int flag = dcrwMapper.deleteByExample(dcrwExample);
        if (flag != 0){
            return true;
        }else
            return false;
    }


    public boolean deleteDcrwById(int id) {
        dcDcrwExample dcrwExample = new dcDcrwExample();
        dcrwExample.createCriteria()
                .andIdEqualTo(id);
        int flag = dcrwMapper.deleteByExample(dcrwExample);
        if (flag != 0){
            return true;
        }else
            return false;
    }
}
