prompt PL/SQL Developer import file
prompt Created on 2013年12月24日 by 01311917
set feedback off
set define off
prompt Disabling triggers for WB_WALLPAPER...
alter table WB_WALLPAPER disable all triggers;
prompt Loading WB_WALLPAPER...
insert into WB_WALLPAPER (ID, TITLE, URL, WIDTH, HEIGHT)
values (1, '壁纸1', '/portal/img/wallpaper/wallpaper1.jpg', 1920, 1080);
insert into WB_WALLPAPER (ID, TITLE, URL, WIDTH, HEIGHT)
values (2, '壁纸2', '/portal/img/wallpaper/wallpaper2.jpg', 1920, 1080);
insert into WB_WALLPAPER (ID, TITLE, URL, WIDTH, HEIGHT)
values (3, '壁纸3', '/portal/img/wallpaper/wallpaper3.jpg', 1920, 1080);
insert into WB_WALLPAPER (ID, TITLE, URL, WIDTH, HEIGHT)
values (4, '壁纸4', '/portal/img/wallpaper/wallpaper4.jpg', 1920, 1080);
insert into WB_WALLPAPER (ID, TITLE, URL, WIDTH, HEIGHT)
values (5, '壁纸5', '/portal/img/wallpaper/wallpaper5.jpg', 1920, 1080);
insert into WB_WALLPAPER (ID, TITLE, URL, WIDTH, HEIGHT)
values (6, '壁纸6', '/portal/img/wallpaper/wallpaper6.jpg', 1920, 1080);
insert into WB_WALLPAPER (ID, TITLE, URL, WIDTH, HEIGHT)
values (7, '壁纸7', '/portal/img/wallpaper/wallpaper7.jpg', 1920, 1080);
insert into WB_WALLPAPER (ID, TITLE, URL, WIDTH, HEIGHT)
values (8, '壁纸8', '/portal/img/wallpaper/wallpaper8.jpg', 1920, 1080);
insert into WB_WALLPAPER (ID, TITLE, URL, WIDTH, HEIGHT)
values (9, '壁纸9', '/portal/img/wallpaper/wallpaper9.jpg', 1920, 1080);
commit;
prompt 9 records loaded
prompt Enabling triggers for WB_WALLPAPER...
alter table WB_WALLPAPER enable all triggers;
set feedback on
set define on
prompt Done.
