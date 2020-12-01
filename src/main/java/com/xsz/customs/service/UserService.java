package com.xsz.customs.service;

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

    //由于更新用户数据与增加一位用户的操作有相似之处，所以合并在一起
    public void createOrUpdate(dcUser user){
        dcUserExample userexample = new dcUserExample();
        userexample.createCriteria()
                .andDcGqdmEqualTo(user.getDcGqdm());
        List<dcUser> users = userMapper.selectByExample(userexample);
        //若通过海关编号没有查到用户，则说明此处需要注册一个用户
        if (users.size() == 0){
            userMapper.insert(user);
        }
        //若海关编号在数据库中已有存储，说明此时希望更新海关信息
        else {
            //数据库中查到的需要被修改的老user
            dcUser dbuser = users.get(0);
            dcUser updateUser = new dcUser();
            updateUser.setDcGqdm(user.getDcGqdm());
            updateUser.setDcGqname(user.getDcGqpword());
            dcUserExample example = new dcUserExample();
            example.createCriteria()
                    .andIdEqualTo(dbuser.getId());
            userMapper.updateByExampleSelective(updateUser,example);
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
}
