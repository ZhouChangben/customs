package com.xsz.customs.service;

import com.xsz.customs.dto.UserInfoDTO;
import com.xsz.customs.mapper.dcUserExtMapper;
import com.xsz.customs.mapper.dcUserMapper;
import com.xsz.customs.model.dcDwjy;
import com.xsz.customs.model.dcUser;
import com.xsz.customs.model.dcUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private dcUserMapper userMapper;

    @Autowired
    private dcUserExtMapper userExtMapper;

    public dcUser findUserByGqdm(String gqdm){
        dcUserExample example = new dcUserExample();
        example.createCriteria()
                .andDcGqdmEqualTo(gqdm);
        List<dcUser> users = userMapper.selectByExample(example);
        if (users.size() != 0){
            dcUser user = users.get(0);
            return user;
        }
        else {
            return null;
        }
    }

    public boolean create(dcUser user){
        dcUserExample userexample = new dcUserExample();
        userexample.createCriteria()
                .andDcGqdmEqualTo(user.getDcGqdm());
        List<dcUser> users = userMapper.selectByExample(userexample);
        //若通过海关编号没有查到用户，则说明此处需要注册一个用户
        if (users.size() == 0){
            //这里的user还不能直接插入，需要判断一下用户等级然后加入数据库中
            //此处是判断新增的是二级用户还是三级用户
            if (user.getDcGqdj()!=null && user.getDcGqdj() == 1){
                userMapper.insert(user);
                return true;
            }else {
                String gqdm = user.getDcGqdm();
                String gqdj = gqdm.substring(4);
                int dj = Integer.parseInt(gqdj) + 1;
                user.setDcGqdj(dj);
                userMapper.insert(user);
                return true;
            }
        }else
            return false;
    }

    //更新操作，还是与注册方法写开比较好
    public boolean update(dcUser user){
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
            updateUser.setDcLxr(user.getDcLxr());
            updateUser.setDcLxdh(user.getDcLxdh());
            updateUser.setDcWjqx(user.getDcWjqx());
            updateUser.setDcDjqx(user.getDcDjqx());
            updateUser.setDcZjqx(user.getDcZjqx());
            dcUserExample example = new dcUserExample();
            example.createCriteria()
                    .andIdEqualTo(dbuser.getId());
            userMapper.updateByExampleSelective(updateUser, example);
            return true;
        }
        else
            return false;
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

    //此方法考虑到了分页的情况
    //用于获取用户下属列表的方法,根据不同的用户类型来进行分类讨论
    public List<dcUser> getUserList(List<dcUser> users, int page,int rows,int size){
        int first = (page-1)*rows;
        int last = (page-1)*rows + rows;
        if (last > size){
            last = size;
        }
        return users.subList(first,last);
    }

    //此方法是获得当前关区的所有子关区的方法
    public List<dcUser> getUserList(String gqdm, int gqdj){
        dcUser user = new dcUser();
        user.setDcGqdj(gqdj);
        dcUserExample example = new dcUserExample();
        //当前用户是海关科技司（最高级）
        if (gqdj == 0){
            example.createCriteria()
                    .andDcGqdjEqualTo(1);
            List<dcUser> users = userMapper.selectByExample(example);
            return users;
        }
        //当前用户是二级海关部门
        else if (gqdj == 1){
            String frontdm = gqdm.substring(0,4);
            List<dcUser> users = userExtMapper.SelectByFrontDm(frontdm+"%");
            return users;
        }
        //当前用户是最低级的用户
        else{
            example.createCriteria()
                    .andDcGqdmEqualTo(gqdm);
            List<dcUser> users = userMapper.selectByExample(example);
            return users;
        }
    }

    public boolean deleteUser(String gqdm){
        dcUserExample userExample = new dcUserExample();
        userExample.createCriteria()
                .andDcGqdmEqualTo(gqdm);
        int flag = userMapper.deleteByExample(userExample);
        if (flag != 0){
            return true;
        }else
            return false;
    }

    public String getMaxSubUserDm(String gqdm){
        String frontdm = gqdm.substring(0,4);
        int maxNum = userExtMapper.GetTheNextGqdm(frontdm+"%");
        String reardm;
        if (maxNum < 10){
            reardm = Integer.toString(maxNum);
            reardm ='0'+reardm;
        }
        else {
            reardm = Integer.toString(maxNum);
        }
        return frontdm+reardm;
    }

    public List<dcUser> findAllSubuser(){
        dcUserExample userExample = new dcUserExample();
        userExample.createCriteria()
                .andDcGqdjEqualTo(1);
        List<dcUser> users = userMapper.selectByExample(userExample);
        return users;
    }

    public List<UserInfoDTO> getUserInfo(dcUser user,List<dcUser> users){

        List<UserInfoDTO> userInfoDTOS = new ArrayList<>();
        UserInfoDTO userInfoDTO1 = new UserInfoDTO();
        userInfoDTO1.setGqdm(user.getDcGqdm());
        userInfoDTO1.setGqName(user.getDcGqname());
        userInfoDTO1.setDjLxr(user.getDcDjlxr());
        userInfoDTO1.setDjLxdh(user.getDcDjlxdh());
        userInfoDTO1.setDjFzr(user.getDcDjfzr());
        userInfoDTO1.setDjFzrdh(user.getDcDjfzdh());
        userInfoDTO1.setWjLxr(user.getDcWjlxr());
        userInfoDTO1.setWjLxdh(user.getDcWjlxdh());
        userInfoDTO1.setWjFzr(user.getDcWjfzr());
        userInfoDTO1.setWjFzrdh(user.getDcWjfzdh());
        userInfoDTO1.setZjLxr(user.getDcZjlxr());
        userInfoDTO1.setZjLxdh(user.getDcZjlxdh());
        userInfoDTO1.setZjFzr(user.getDcZjfzr());
        userInfoDTO1.setZjFzrdh(user.getDcZjfzdh());

        userInfoDTOS.add(userInfoDTO1);
        if (users != null) {
            for (dcUser dcuser : users) {
                UserInfoDTO userInfoDTO = new UserInfoDTO();
                userInfoDTO.setGqdm(dcuser.getDcGqdm());
                userInfoDTO.setGqName(dcuser.getDcGqname());
                userInfoDTO.setDjLxr(dcuser.getDcDjlxr());
                userInfoDTO.setDjLxdh(dcuser.getDcDjlxdh());
                userInfoDTO.setDjFzr(dcuser.getDcDjfzr());
                userInfoDTO.setDjFzrdh(dcuser.getDcDjfzdh());
                userInfoDTO.setWjLxr(dcuser.getDcWjlxr());
                userInfoDTO.setWjLxdh(dcuser.getDcWjlxdh());
                userInfoDTO.setWjFzr(dcuser.getDcWjfzr());
                userInfoDTO.setWjFzrdh(dcuser.getDcWjfzdh());
                userInfoDTO.setZjLxr(dcuser.getDcZjlxr());
                userInfoDTO.setZjLxdh(dcuser.getDcZjlxdh());
                userInfoDTO.setZjFzr(dcuser.getDcZjfzr());
                userInfoDTO.setZjFzrdh(dcuser.getDcZjfzdh());

                userInfoDTOS.add(userInfoDTO);
            }
        }
        return userInfoDTOS;
    }
    public List<UserInfoDTO> getUserInfo(dcUser user){
        List<UserInfoDTO> userInfoDTOS = new ArrayList<>();
        UserInfoDTO userInfoDTO1 = new UserInfoDTO();
        userInfoDTO1.setGqdm(user.getDcGqdm());
        userInfoDTO1.setGqName(user.getDcGqname());
        userInfoDTO1.setDjLxr(user.getDcDjlxr());
        userInfoDTO1.setDjLxdh(user.getDcDjlxdh());
        userInfoDTO1.setDjFzr(user.getDcDjfzr());
        userInfoDTO1.setDjFzrdh(user.getDcDjfzdh());
        userInfoDTO1.setWjLxr(user.getDcWjlxr());
        userInfoDTO1.setWjLxdh(user.getDcWjlxdh());
        userInfoDTO1.setWjFzr(user.getDcWjfzr());
        userInfoDTO1.setWjFzrdh(user.getDcWjfzdh());
        userInfoDTO1.setZjLxr(user.getDcZjlxr());
        userInfoDTO1.setZjLxdh(user.getDcZjlxdh());
        userInfoDTO1.setZjFzr(user.getDcZjfzr());
        userInfoDTO1.setZjFzrdh(user.getDcZjfzdh());
        userInfoDTOS.add(userInfoDTO1);

        return userInfoDTOS;
    }

    public UserInfoDTO getUserInfomation(dcUser user){
        UserInfoDTO userInfoDTO1 = new UserInfoDTO();
        userInfoDTO1.setGqdm(user.getDcGqdm());
        userInfoDTO1.setGqName(user.getDcGqname());
        userInfoDTO1.setLxr(user.getDcLxr());
        userInfoDTO1.setLxrdh(user.getDcLxdh());
        userInfoDTO1.setDjLxr(user.getDcDjlxr());
        userInfoDTO1.setDjLxdh(user.getDcDjlxdh());
        userInfoDTO1.setDjFzr(user.getDcDjfzr());
        userInfoDTO1.setDjFzrdh(user.getDcDjfzdh());
        userInfoDTO1.setWjLxr(user.getDcWjlxr());
        userInfoDTO1.setWjLxdh(user.getDcWjlxdh());
        userInfoDTO1.setWjFzr(user.getDcWjfzr());
        userInfoDTO1.setWjFzrdh(user.getDcWjfzdh());
        userInfoDTO1.setZjLxr(user.getDcZjlxr());
        userInfoDTO1.setZjLxdh(user.getDcZjlxdh());
        userInfoDTO1.setZjFzr(user.getDcZjfzr());
        userInfoDTO1.setZjFzrdh(user.getDcZjfzdh());


        return userInfoDTO1;
    }

    public List<UserInfoDTO> getUserInfoForSub(dcUser user,dcUser fUser){
        boolean wjqx = user.getDcWjqx();
        boolean djqx = user.getDcDjqx();
        boolean zjqx = user.getDcZjqx();

        List<UserInfoDTO> userInfoDTOS = new ArrayList<>();
        UserInfoDTO userInfoDTO1 = new UserInfoDTO();
        userInfoDTO1.setGqdm(fUser.getDcGqdm());
        userInfoDTO1.setGqName(fUser.getDcGqname());
        if (djqx == true) {
            userInfoDTO1.setDjLxr(fUser.getDcDjlxr());
            userInfoDTO1.setDjLxdh(fUser.getDcDjlxdh());
            userInfoDTO1.setDjFzr(fUser.getDcDjfzr());
            userInfoDTO1.setDjFzrdh(fUser.getDcDjfzdh());
        }
        if (wjqx == true) {
            userInfoDTO1.setWjLxr(fUser.getDcWjlxr());
            userInfoDTO1.setWjLxdh(fUser.getDcWjlxdh());
            userInfoDTO1.setWjFzr(fUser.getDcWjfzr());
            userInfoDTO1.setWjFzrdh(fUser.getDcWjfzdh());
        }
        if (zjqx == true) {
            userInfoDTO1.setZjLxr(fUser.getDcZjlxr());
            userInfoDTO1.setZjLxdh(fUser.getDcZjlxdh());
            userInfoDTO1.setZjFzr(fUser.getDcZjfzr());
            userInfoDTO1.setZjFzrdh(fUser.getDcZjfzdh());
        }
        userInfoDTOS.add(userInfoDTO1);
        return userInfoDTOS;
    }

    public List<UserInfoDTO> getUserInfoListPage(List<UserInfoDTO> userInfoDTOS, Integer page, Integer rows, int size) {
        int first = (page-1)*rows;
        int last = (page-1)*rows + rows;
        if (last > size){
            last = size;
        }
        return userInfoDTOS.subList(first,last);
    }

    public boolean updateUserInfomation(dcUser user){
        dcUserExample example = new dcUserExample();
        example.createCriteria()
                .andDcGqdmEqualTo(user.getDcGqdm());
        List<dcUser> users = userMapper.selectByExample(example);
        if (users.size() != 0){
            int flag = userMapper.updateByExampleSelective(user,example);
            if (flag == 0){
                return false;
            }
            if (flag == 1){
                return true;
            }
        }
        return false;
    }

    public dcUser findFatherGq(String gqdm) {
        //dcUser user = new dcUser();
        dcUserExample example = new dcUserExample();
        String subgqdm = gqdm.substring(0,4);
        String fgqdm = subgqdm + "00";
        example.createCriteria()
                .andDcGqdmEqualTo(fgqdm);
        List<dcUser> users = userMapper.selectByExample(example);
        if (users != null){
            return users.get(0);
        }
        return null;
    }
}
