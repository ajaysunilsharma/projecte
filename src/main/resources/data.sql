---password
insert into users(username,password,enabled) values('user','$2a$10$U99JXDN4CrzG5XiV/fkRgu.vc0daSIjMTa4aS.oi58P5bvlRO0hhm',true);
---root
insert into users(username,password,enabled) values('admin','$2a$10$rXXBv1.qxJNB4XHIllWRUO7OYf.xZdY97iMQRBfeA8MS0ObHuiq..',true);
---override
insert into users(username,password,enabled) values('sudo','$2a$10$4fn5IZcMoqkqna8j/5MzYuL953ml/otS/TZ0bPdebnPjqx7080ppi',true);

insert into authorities(username,authority) values('user','ROLE_USER');
insert into authorities(username,authority) values('admin','ROLE_ADMIN');
insert into authorities(username,authority) values('sudo','ROLE_GOD');
