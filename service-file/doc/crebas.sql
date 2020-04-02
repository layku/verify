drop table if exists t_file_info;

/*==============================================================*/
/* Table: t_file_info                                           */
/*==============================================================*/
create table t_file_info
(
   id                   int not null auto_increment comment ''表ID'',
   real_name            varchar(200) comment ''文件真实名'',
   name                 varchar(100) comment ''系统生成文件名'',
   system               varchar(100) comment ''文件所属系统'',
   type                 varchar(100) comment ''文件所属分类'',
   uri                  varchar(200) comment ''文件地址'',
   md5                  varchar(50) comment ''文件MD5值'',
   size                 int comment ''文件大小'',
   content_type         varchar(100) comment ''文件类型'',
   create_time          int comment ''上传时间'',
   year                 int comment ''上传年'',
   month                int comment ''上传月'',
   day                  int comment ''上传日'',
   primary key (id)
);

alter table t_file_info comment ''文件信息表'';
