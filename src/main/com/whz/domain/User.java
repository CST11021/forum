package com.whz.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "t_user")
public class User extends BaseDomain {

    public static final int USER_LOCK = 1;/*锁定用户对应的状态值*/
    public static final int USER_UNLOCK = 0;/*用户解锁对应的状态值*/
    public static final int FORUM_ADMIN = 2;/*管理员类型*/
    public static final int NORMAL_USER = 1;/*普通用户类型*/
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
    private int userId;/*用户编号，主键*/

    @Column(name = "user_name")
	private String userName;/*用户名*/
    
    @Column(name = "user_type")
    private int userType = NORMAL_USER;/*用户类型（普通、管理员）*/

    @Column(name = "last_ip")
	private String lastIp;/*最后登入IP*/
	
	@Column(name = "last_visit")
	private Date lastVisit;/*最后登入时间*/
	private String password;/*密码*/
	private int locked ;/*是否被锁定*/
	private int credit;/*积分*/

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(name = "t_board_manager", joinColumns = {@JoinColumn(name ="user_id" )}, inverseJoinColumns = {@JoinColumn(name = "board_id") })
	private Set<Board> manBoards = new HashSet<Board>();

	public boolean isForumAdmin(){
		boolean isAdmin = false;
		if(FORUM_ADMIN == userType){
			isAdmin = true;
		}
		return isAdmin;
	}

	/*getter and setter...*/
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
	}
	public Set<Board> getManBoards()
    {
        return manBoards;
    }
    public void setManBoards(Set<Board> manBoards)
    {
        this.manBoards = manBoards;
    }
    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
    public int getUserType()
    {
        return userType;
    }
    public void setUserType(int userType)
    {
        this.userType = userType;
    }
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	public Date getLastVisit() {
		return lastVisit;
	}
	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

}
