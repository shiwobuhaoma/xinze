package com.xinze.xinze.module.login.modle;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lxf
 * 当前登陆用户对象
 */
public class UserEntity implements Serializable {
    /**
    * 编号
    **/
    private String id;

    /**
    * 归属公司
    **/
    private String company_id;

    /**
    * 归属部门
    **/
    private String office_id;

    /**
    * 登录名
    **/
    private String login_name;

    /**
    * 密码
    **/
    private String password;

    /**
    * 工号
    **/
    private String no;

    /**
    * 姓名
    **/
    private String name;

    /**
    * 邮箱
    **/
    private String email;

    /**
    * 电话
    **/
    private String phone;

    /**
    * 手机
    **/
    private String mobile;

    /**
    * 用户类型
    **/
    private String user_type;

    /**
    * 用户头像
    **/
    private String photo;

    /**
    * 最后登陆IP
    **/
    private String login_ip;

    /**
    * 最后登陆时间
    **/
    private Date login_date;

    /**
    * 是否可登录
    **/
    private String login_flag;

    /**
    * 创建者
    **/
    private String create_by;

    /**
    * 创建时间
    **/
    private Date create_date;

    /**
    * 更新者
    **/
    private String update_by;

    /**
    * 更新时间
    **/
    private Date update_date;

    /**
    * 备注信息
    **/
    private String remarks;

    /**
    * 删除标记
    **/
    private String del_flag;

    /**
    * 姓名首字母
    **/
    private String first_letter;

    /**
    * 姓名全拼
    **/
    private String pinyin;

    /**
    * 移动端登陆token
    **/
    private String sessionid;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    /**
     * 客户端判断用户是否登陆的标记
     */
    private boolean isLogin;
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public UserEntity withId(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCompany_id() {
        return company_id;
    }

    public UserEntity withCompany_id(String company_id) {
        this.setCompany_id(company_id);
        return this;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id == null ? null : company_id.trim();
    }

    public String getOffice_id() {
        return office_id;
    }

    public UserEntity withOffice_id(String office_id) {
        this.setOffice_id(office_id);
        return this;
    }

    public void setOffice_id(String office_id) {
        this.office_id = office_id == null ? null : office_id.trim();
    }

    public String getLogin_name() {
        return login_name;
    }

    public UserEntity withLogin_name(String login_name) {
        this.setLogin_name(login_name);
        return this;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name == null ? null : login_name.trim();
    }

    public String getPassword() {
        return password;
    }

    public UserEntity withPassword(String password) {
        this.setPassword(password);
        return this;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNo() {
        return no;
    }

    public UserEntity withNo(String no) {
        this.setNo(no);
        return this;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getName() {
        return name;
    }

    public UserEntity withName(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public UserEntity withEmail(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public UserEntity withPhone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public UserEntity withMobile(String mobile) {
        this.setMobile(mobile);
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getUser_type() {
        return user_type;
    }

    public UserEntity withUser_type(String user_type) {
        this.setUser_type(user_type);
        return this;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type == null ? null : user_type.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public UserEntity withPhoto(String photo) {
        this.setPhoto(photo);
        return this;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getLogin_ip() {
        return login_ip;
    }

    public UserEntity withLogin_ip(String login_ip) {
        this.setLogin_ip(login_ip);
        return this;
    }

    public void setLogin_ip(String login_ip) {
        this.login_ip = login_ip == null ? null : login_ip.trim();
    }

    public Date getLogin_date() {
        return login_date;
    }

    public UserEntity withLogin_date(Date login_date) {
        this.setLogin_date(login_date);
        return this;
    }

    public void setLogin_date(Date login_date) {
        this.login_date = login_date;
    }

    public String getLogin_flag() {
        return login_flag;
    }

    public UserEntity withLogin_flag(String login_flag) {
        this.setLogin_flag(login_flag);
        return this;
    }

    public void setLogin_flag(String login_flag) {
        this.login_flag = login_flag == null ? null : login_flag.trim();
    }

    public String getCreate_by() {
        return create_by;
    }

    public UserEntity withCreate_by(String create_by) {
        this.setCreate_by(create_by);
        return this;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by == null ? null : create_by.trim();
    }

    public Date getCreate_date() {
        return create_date;
    }

    public UserEntity withCreate_date(Date create_date) {
        this.setCreate_date(create_date);
        return this;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public UserEntity withUpdate_by(String update_by) {
        this.setUpdate_by(update_by);
        return this;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by == null ? null : update_by.trim();
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public UserEntity withUpdate_date(Date update_date) {
        this.setUpdate_date(update_date);
        return this;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public String getRemarks() {
        return remarks;
    }

    public UserEntity withRemarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDel_flag() {
        return del_flag;
    }

    public UserEntity withDel_flag(String del_flag) {
        this.setDel_flag(del_flag);
        return this;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag == null ? null : del_flag.trim();
    }

    public String getFirst_letter() {
        return first_letter;
    }

    public UserEntity withFirst_letter(String first_letter) {
        this.setFirst_letter(first_letter);
        return this;
    }

    public void setFirst_letter(String first_letter) {
        this.first_letter = first_letter == null ? null : first_letter.trim();
    }

    public String getPinyin() {
        return pinyin;
    }

    public UserEntity withPinyin(String pinyin) {
        this.setPinyin(pinyin);
        return this;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin == null ? null : pinyin.trim();
    }

    public String getSessionid() {
        return sessionid;
    }

    public UserEntity withSessionid(String sessionid) {
        this.setSessionid(sessionid);
        return this;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid == null ? null : sessionid.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", company_id=").append(company_id);
        sb.append(", office_id=").append(office_id);
        sb.append(", login_name=").append(login_name);
        sb.append(", password=").append(password);
        sb.append(", no=").append(no);
        sb.append(", name=").append(name);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", mobile=").append(mobile);
        sb.append(", user_type=").append(user_type);
        sb.append(", photo=").append(photo);
        sb.append(", login_ip=").append(login_ip);
        sb.append(", login_date=").append(login_date);
        sb.append(", login_flag=").append(login_flag);
        sb.append(", create_by=").append(create_by);
        sb.append(", create_date=").append(create_date);
        sb.append(", update_by=").append(update_by);
        sb.append(", update_date=").append(update_date);
        sb.append(", remarks=").append(remarks);
        sb.append(", del_flag=").append(del_flag);
        sb.append(", first_letter=").append(first_letter);
        sb.append(", pinyin=").append(pinyin);
        sb.append(", sessionid=").append(sessionid);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserEntity other = (UserEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompany_id() == null ? other.getCompany_id() == null : this.getCompany_id().equals(other.getCompany_id()))
            && (this.getOffice_id() == null ? other.getOffice_id() == null : this.getOffice_id().equals(other.getOffice_id()))
            && (this.getLogin_name() == null ? other.getLogin_name() == null : this.getLogin_name().equals(other.getLogin_name()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getNo() == null ? other.getNo() == null : this.getNo().equals(other.getNo()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getUser_type() == null ? other.getUser_type() == null : this.getUser_type().equals(other.getUser_type()))
            && (this.getPhoto() == null ? other.getPhoto() == null : this.getPhoto().equals(other.getPhoto()))
            && (this.getLogin_ip() == null ? other.getLogin_ip() == null : this.getLogin_ip().equals(other.getLogin_ip()))
            && (this.getLogin_date() == null ? other.getLogin_date() == null : this.getLogin_date().equals(other.getLogin_date()))
            && (this.getLogin_flag() == null ? other.getLogin_flag() == null : this.getLogin_flag().equals(other.getLogin_flag()))
            && (this.getCreate_by() == null ? other.getCreate_by() == null : this.getCreate_by().equals(other.getCreate_by()))
            && (this.getCreate_date() == null ? other.getCreate_date() == null : this.getCreate_date().equals(other.getCreate_date()))
            && (this.getUpdate_by() == null ? other.getUpdate_by() == null : this.getUpdate_by().equals(other.getUpdate_by()))
            && (this.getUpdate_date() == null ? other.getUpdate_date() == null : this.getUpdate_date().equals(other.getUpdate_date()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getDel_flag() == null ? other.getDel_flag() == null : this.getDel_flag().equals(other.getDel_flag()))
            && (this.getFirst_letter() == null ? other.getFirst_letter() == null : this.getFirst_letter().equals(other.getFirst_letter()))
            && (this.getPinyin() == null ? other.getPinyin() == null : this.getPinyin().equals(other.getPinyin()))
            && (this.getSessionid() == null ? other.getSessionid() == null : this.getSessionid().equals(other.getSessionid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompany_id() == null) ? 0 : getCompany_id().hashCode());
        result = prime * result + ((getOffice_id() == null) ? 0 : getOffice_id().hashCode());
        result = prime * result + ((getLogin_name() == null) ? 0 : getLogin_name().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getNo() == null) ? 0 : getNo().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getUser_type() == null) ? 0 : getUser_type().hashCode());
        result = prime * result + ((getPhoto() == null) ? 0 : getPhoto().hashCode());
        result = prime * result + ((getLogin_ip() == null) ? 0 : getLogin_ip().hashCode());
        result = prime * result + ((getLogin_date() == null) ? 0 : getLogin_date().hashCode());
        result = prime * result + ((getLogin_flag() == null) ? 0 : getLogin_flag().hashCode());
        result = prime * result + ((getCreate_by() == null) ? 0 : getCreate_by().hashCode());
        result = prime * result + ((getCreate_date() == null) ? 0 : getCreate_date().hashCode());
        result = prime * result + ((getUpdate_by() == null) ? 0 : getUpdate_by().hashCode());
        result = prime * result + ((getUpdate_date() == null) ? 0 : getUpdate_date().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getDel_flag() == null) ? 0 : getDel_flag().hashCode());
        result = prime * result + ((getFirst_letter() == null) ? 0 : getFirst_letter().hashCode());
        result = prime * result + ((getPinyin() == null) ? 0 : getPinyin().hashCode());
        result = prime * result + ((getSessionid() == null) ? 0 : getSessionid().hashCode());
        return result;
    }
}