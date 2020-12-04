package com.xsz.customs.service;

import com.xsz.customs.mapper.dcUserExtMapper;
import com.xsz.customs.mapper.dcUserMapper;
import com.xsz.customs.model.dcUser;
import com.xsz.customs.model.dcUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private dcUserMapper userMapper;

    @Autowired
    private dcUserExtMapper userExtMapper;

    public void create(dcUser user){
        dcUserExample userexample = new dcUserExample();
        userexample.createCriteria()
                .andDcGqdmEqualTo(user.getDcGqdm());
        List<dcUser> users = userMapper.selectByExample(userexample);
        //若通过海关编号没有查到用户，则说明此处需要注册一个用户
        if (users.size() == 0){
            //这里的user还不能直接插入，需要判断一下用户等级然后加入数据库中
            String gqdm = user.getDcGqdm();
            String gqdj = gqdm.substring(4);
            int dj = Integer.parseInt(gqdj) + 1;
            user.setDcGqdj(dj);
            userMapper.insert(user);
        }
    }

    //更新操作，还是与注册方法写开比较好
    public void update(dcUser user){
        dcUserExample userexample = new dcUserExample();
        userexample.createCriteria()
                .andDcGqdmEqualTo(user.getDcGqdm());
        List<dcUser> users = userMapper.selectByExample(userexample);
        if (users.size() != 0){
            //若海关编号在数据库中已有存储，说明此时希望更新海关信息
            //数据库中查到的需要被修改的老user
            dcUser dbuser = users.get(0);
            dcUser updateUser = new dcUser();
            updateUser.setDcGqdm(user.getDcGqdm());
            updateUser.setDcGqname(user.getDcGqname());
            updateUser.setDcGqpword(user.getDcGqpword());
            dcUserExample example = new dcUserExample();
            example.createCriteria()
                    .andIdEqualTo(dbuser.getId());
            userMapper.updateByExampleSelective(updateUser, example);
        }
    }
    //用于检验账号密码是否正确，若正确则返回1。如需要显示登录失败的原因可以后期进行修改。
    public boolean login(dcUser user){
        dcUserExample userexample = new dcUserExample();
        userexample.createCriteria()
                .andDcGqdmEqualTo(user.getDcGqdm());
        List<dcUser> users = userMapper.selectByExample(userexample);
        if (users.size() != 0){
            dcUser user1 = users.get(0);
            if (user1.getDcGqpword().equals(user.getDcGqpword())){
                return true;
            }
            else {
                //密码错误
                return false;
            }
        }
        else{
            //账号错误
            return false;
        }
    }

    //用于获取用户下属列表的方法,根据不同的用户类型来进行分类讨论
    public List<dcUser> getUserList(String gqdm,int gqdj){
        dcUser user = new dcUser();
        user.setDcGqdj(gqdj);
        dcUserExample example = new dcUserExample();
        //当前用户是海关科技司（最高级）
        if (gqdj == 0){
            example.createCriteria()
                    .andDcGqdjEqualTo(1);
            List<dcUser> users = userMapper.selectByExample(example);
            //测试代码
            /*for (dcUser dcUser : users) {
                System.out.println(dcUser.getDcGqname());
            }*/
            //测试代码结束
            return users;
        }
        //当前用户是二级海关部门
        else if (gqdj == 1){
            String frontdm = gqdm.substring(0,4);
            //String reardm = gqdm.substring(4);
            List<dcUser> users = userExtMapper.SelectByFrontDm(frontdm+"%");
            //测试代码
            /*if (users.size() == 0) {
                System.out.println("获取失败");
            }
            else {
                for (dcUser dcUser : users) {
                    System.out.println(dcUser.getDcGqname());
                }
            }*/
            //测试代码结束
            if (users.size() != 0)
                return users;
            else
                return null;
        }
        //当前用户是最低级的用户
        else{
            return null;
        }
    }

    public void deleteUser(String gqdm){
        dcUserExample userExample = new dcUserExample();
        userExample.createCriteria()
                .andDcGqdmEqualTo(gqdm);
        userMapper.deleteByExample(userExample);
    }
}
