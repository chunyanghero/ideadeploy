------------------------------------------------
-- Export file for user SHOWCASE3             --
-- Created by 01311917 on 2013/12/24, 9:40:48 --
------------------------------------------------

spool showcase3.log

prompt
prompt Creating table OPERATION_LOG
prompt ============================
prompt
create table SHOWCASE3.OPERATION_LOG
(
  ID             NUMBER not null,
  USER_ID        NUMBER,
  USER_NAME      VARCHAR2(36),
  USER_NICK_NAME VARCHAR2(64),
  OPERATION_TYPE NUMBER,
  GMT_CREATE     DATE not null,
  GMT_MODIFIED   DATE not null,
  MODULE         NUMBER,
  APP_NAME       VARCHAR2(64) not null,
  DESCRIPTION    VARCHAR2(4000),
  ORIGINAL_DATA  VARCHAR2(4000),
  ACTUAL_DATA    VARCHAR2(4000)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SHOWCASE3.OPERATION_LOG
  add constraint OPERATION_LOG_PK primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table UPLOAD_FILE
prompt ==========================
prompt
create table SHOWCASE3.UPLOAD_FILE
(
  ID             INTEGER not null,
  FILE_NAME      VARCHAR2(400),
  SAVE_FILE_NAME VARCHAR2(400),
  FILE_PATH_1    VARCHAR2(254),
  FILE_PATH_2    VARCHAR2(254),
  STATUS         NUMBER,
  TYPE           NUMBER,
  REMARKS        VARCHAR2(254),
  LAST_UPD_BY    VARCHAR2(32),
  LAST_UPD_DT    DATE,
  CREATED_BY     VARCHAR2(32),
  CREATED_DT     DATE default SYSDATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column SHOWCASE3.UPLOAD_FILE.ID
  is '主键';
comment on column SHOWCASE3.UPLOAD_FILE.FILE_NAME
  is '文件名';
comment on column SHOWCASE3.UPLOAD_FILE.SAVE_FILE_NAME
  is '实际保存的文件名';
comment on column SHOWCASE3.UPLOAD_FILE.FILE_PATH_1
  is '文件保存路径1';
comment on column SHOWCASE3.UPLOAD_FILE.FILE_PATH_2
  is '文件保存路径2';
comment on column SHOWCASE3.UPLOAD_FILE.STATUS
  is '文件状态';
comment on column SHOWCASE3.UPLOAD_FILE.TYPE
  is '文件类型';
comment on column SHOWCASE3.UPLOAD_FILE.REMARKS
  is '备注';
comment on column SHOWCASE3.UPLOAD_FILE.LAST_UPD_BY
  is '最后一次修改人';
comment on column SHOWCASE3.UPLOAD_FILE.LAST_UPD_DT
  is '最后一次修改时间';
comment on column SHOWCASE3.UPLOAD_FILE.CREATED_BY
  is '创建人';
comment on column SHOWCASE3.UPLOAD_FILE.CREATED_DT
  is '创建时间';
alter table SHOWCASE3.UPLOAD_FILE
  add constraint PK_ID primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table WB_MEMBER
prompt ========================
prompt
create table SHOWCASE3.WB_MEMBER
(
  ID               NUMBER(20) not null,
  USERNAME         VARCHAR2(128),
  DOCK             VARCHAR2(255),
  DESK1            VARCHAR2(255),
  DESK2            VARCHAR2(255),
  DESK3            VARCHAR2(255),
  DESK4            VARCHAR2(255),
  DESK5            VARCHAR2(255),
  DESK6            VARCHAR2(255),
  DESKNAME1        VARCHAR2(100),
  DESKNAME2        VARCHAR2(100),
  DESKNAME3        VARCHAR2(100),
  DESKNAME4        VARCHAR2(100),
  DESKNAME5        VARCHAR2(100),
  DESKNAME6        VARCHAR2(100),
  APPXY            VARCHAR2(32),
  DOCKPOS          VARCHAR2(32),
  WALLPAPER_ID     NUMBER(11),
  WALLPAPERWEBSITE VARCHAR2(255),
  WALLPAPERSTATE   NUMBER(4),
  WALLPAPERTYPE    VARCHAR2(32),
  SKIN             VARCHAR2(32),
  DESKNUM          NUMBER(2) default 3,
  GMT_MODIFIED     DATE,
  GMT_CREATE       DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column SHOWCASE3.WB_MEMBER.DOCK
  is '[应用码头]应用id，用","相连';
comment on column SHOWCASE3.WB_MEMBER.DESK1
  is '[桌面1]应用id，用","相连';
comment on column SHOWCASE3.WB_MEMBER.DESK2
  is '[桌面2]应用id，用","相连';
comment on column SHOWCASE3.WB_MEMBER.DESK3
  is '[桌面3]应用id，用","相连';
comment on column SHOWCASE3.WB_MEMBER.DESK4
  is '[桌面4]应用id，用","相连';
comment on column SHOWCASE3.WB_MEMBER.DESK5
  is '[桌面5]应用id，用","相连';
comment on column SHOWCASE3.WB_MEMBER.DESKNAME1
  is '桌面1名称';
comment on column SHOWCASE3.WB_MEMBER.DESKNAME2
  is '桌面2名称';
comment on column SHOWCASE3.WB_MEMBER.DESKNAME3
  is '桌面3名称';
comment on column SHOWCASE3.WB_MEMBER.DESKNAME4
  is '桌面4名称';
comment on column SHOWCASE3.WB_MEMBER.DESKNAME5
  is '桌面5名称';
comment on column SHOWCASE3.WB_MEMBER.DESKNAME6
  is '桌面6名称';
comment on column SHOWCASE3.WB_MEMBER.APPXY
  is '图标排列方式';
comment on column SHOWCASE3.WB_MEMBER.DOCKPOS
  is '应用码头位置，参数：top,left,right';
comment on column SHOWCASE3.WB_MEMBER.WALLPAPERSTATE
  is '1系统壁纸、2自定义壁纸、3网络地址';
comment on column SHOWCASE3.WB_MEMBER.WALLPAPERTYPE
  is '壁纸显示方式：tianchong,shiying,pingpu,lashen,juzhong';
comment on column SHOWCASE3.WB_MEMBER.SKIN
  is '窗口皮肤';
comment on column SHOWCASE3.WB_MEMBER.DESKNUM
  is '个人桌面数量默认3';
comment on column SHOWCASE3.WB_MEMBER.GMT_MODIFIED
  is '最近修改时间';
comment on column SHOWCASE3.WB_MEMBER.GMT_CREATE
  is '创建时间';
alter table SHOWCASE3.WB_MEMBER
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table WB_MEMBER_APP
prompt ============================
prompt
create table SHOWCASE3.WB_MEMBER_APP
(
  ID        NUMBER(20) not null,
  REALID    NUMBER(20),
  NAME      VARCHAR2(255),
  ICON      VARCHAR2(255),
  URL       VARCHAR2(255),
  TYPE      VARCHAR2(32),
  WIDTH     NUMBER(11),
  HEIGHT    NUMBER(11),
  ISRESIZE  NUMBER(4),
  ISOPENMAX NUMBER(4),
  ISSETBAR  NUMBER(4),
  ISFLASH   NUMBER(4),
  DT        DATE,
  LASTDT    DATE,
  FOLDER_ID NUMBER(20),
  MEMBER_ID NUMBER(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column SHOWCASE3.WB_MEMBER_APP.REALID
  is '资源表id';
comment on column SHOWCASE3.WB_MEMBER_APP.NAME
  is '图标名称';
comment on column SHOWCASE3.WB_MEMBER_APP.ICON
  is '图标图片';
comment on column SHOWCASE3.WB_MEMBER_APP.TYPE
  is '应用类型';
comment on column SHOWCASE3.WB_MEMBER_APP.WIDTH
  is '窗口宽度';
comment on column SHOWCASE3.WB_MEMBER_APP.HEIGHT
  is '窗口高度';
comment on column SHOWCASE3.WB_MEMBER_APP.ISRESIZE
  is '是否能对窗口进行拉伸';
comment on column SHOWCASE3.WB_MEMBER_APP.ISOPENMAX
  is '是否打开直接最大化';
comment on column SHOWCASE3.WB_MEMBER_APP.ISSETBAR
  is '窗口是否有评分和介绍按钮';
comment on column SHOWCASE3.WB_MEMBER_APP.ISFLASH
  is '是否为flash应用';
comment on column SHOWCASE3.WB_MEMBER_APP.DT
  is '创建时间';
comment on column SHOWCASE3.WB_MEMBER_APP.LASTDT
  is '最后修改时间';
comment on column SHOWCASE3.WB_MEMBER_APP.FOLDER_ID
  is '文件夹id';
comment on column SHOWCASE3.WB_MEMBER_APP.MEMBER_ID
  is '用户id';
alter table SHOWCASE3.WB_MEMBER_APP
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table WB_PWALLPAPER
prompt ============================
prompt
create table SHOWCASE3.WB_PWALLPAPER
(
  ID        NUMBER(20) not null,
  URL       VARCHAR2(255),
  WIDTH     NUMBER(11),
  HEIGHT    NUMBER(11),
  MEMBER_ID NUMBER(20),
  FILE_ID   NUMBER(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column SHOWCASE3.WB_PWALLPAPER.ID
  is '主键';
comment on column SHOWCASE3.WB_PWALLPAPER.URL
  is '访问图片的url';
comment on column SHOWCASE3.WB_PWALLPAPER.WIDTH
  is '宽度';
comment on column SHOWCASE3.WB_PWALLPAPER.HEIGHT
  is '高度';
comment on column SHOWCASE3.WB_PWALLPAPER.MEMBER_ID
  is 'wb_member表id';
comment on column SHOWCASE3.WB_PWALLPAPER.FILE_ID
  is 'upload_file表id';
alter table SHOWCASE3.WB_PWALLPAPER
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table WB_WALLPAPER
prompt ===========================
prompt
create table SHOWCASE3.WB_WALLPAPER
(
  ID     NUMBER(20) not null,
  TITLE  VARCHAR2(255),
  URL    VARCHAR2(255),
  WIDTH  NUMBER(11),
  HEIGHT NUMBER(11)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column SHOWCASE3.WB_WALLPAPER.ID
  is '主键id';
comment on column SHOWCASE3.WB_WALLPAPER.TITLE
  is '墙纸标题';
comment on column SHOWCASE3.WB_WALLPAPER.URL
  is 'url';
comment on column SHOWCASE3.WB_WALLPAPER.WIDTH
  is '宽度';
comment on column SHOWCASE3.WB_WALLPAPER.HEIGHT
  is '高度';
alter table SHOWCASE3.WB_WALLPAPER
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating sequence SEQ_OPERATION_LOG_ID
prompt ======================================
prompt
create sequence SHOWCASE3.SEQ_OPERATION_LOG_ID
minvalue 1
maxvalue 9999999999999999999999999999
start with 45
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_UPLOAD_FILE_ID
prompt ====================================
prompt
create sequence SHOWCASE3.SEQ_UPLOAD_FILE_ID
minvalue 1
maxvalue 9999999999999999999999999999
start with 53
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_WB_MEMBER_APP_ID
prompt ======================================
prompt
create sequence SHOWCASE3.SEQ_WB_MEMBER_APP_ID
minvalue 1
maxvalue 99999999999999999999
start with 106
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_WB_MEMBER_ID
prompt ==================================
prompt
create sequence SHOWCASE3.SEQ_WB_MEMBER_ID
minvalue 1
maxvalue 99999999999999999999
start with 69
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_WB_PWALLPAPER_ID
prompt ======================================
prompt
create sequence SHOWCASE3.SEQ_WB_PWALLPAPER_ID
minvalue 1
maxvalue 99999999999999999999
start with 43
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_WB_WALLPAPER_ID
prompt =====================================
prompt
create sequence SHOWCASE3.SEQ_WB_WALLPAPER_ID
minvalue 1
maxvalue 99999999999999999999
start with 1
increment by 1
cache 20;


spool off
