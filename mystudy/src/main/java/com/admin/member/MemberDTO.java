package com.admin.member;

public class MemberDTO {
   private long mNum;
   private String mId;
   private String mPwd;
   private String mName;
   private String mRnum;
   private String mTel;
   private String mPhone;
   private String mEmail;
   private String field;
   private String field2;
   private int adminOrNot;
   private String mRegDate;
   private int deleteOrNot;
   
   public int getDeleteOrNot() {
	return deleteOrNot;
}
public void setDeleteOrNot(int deleteOrNot) {
	this.deleteOrNot = deleteOrNot;
}
public long getmNum() {
      return mNum;
   }
   public void setmNum(long mNum) {
      this.mNum = mNum;
   }
   public String getmId() {
      return mId;
   }
   public void setmId(String mId) {
      this.mId = mId;
   }
   public String getmPwd() {
      return mPwd;
   }
   public void setmPwd(String mPwd) {
      this.mPwd = mPwd;
   }
   public String getmName() {
      return mName;
   }
   public void setmName(String mName) {
      this.mName = mName;
   }
   public String getmRnum() {
      return mRnum;
   }
   public void setmRnum(String mRnum) {
      this.mRnum = mRnum;
   }
   public String getmTel() {
      return mTel;
   }
   public void setmTel(String mTel) {
      this.mTel = mTel;
   }
   public String getmPhone() {
      return mPhone;
   }
   public void setmPhone(String mPhone) {
      this.mPhone = mPhone;
   }
   public String getmEmail() {
      return mEmail;
   }
   public void setmEmail(String mEmail) {
      this.mEmail = mEmail;
   }
   public String getField() {
      return field;
   }
   public void setField(String field) {
      this.field = field;
   }
   public String getField2() {
      return field2;
   }
   public void setField2(String field2) {
      this.field2 = field2;
   }
   public int getAdminOrNot() {
      return adminOrNot;
   }
   public void setAdminOrNot(int adminOrNot) {
      this.adminOrNot = adminOrNot;
   }
   public String getmRegDate() {
      return mRegDate;
   }
   public void setmRegDate(String mRegDate) {
      this.mRegDate = mRegDate;
   }
   
   
   
}