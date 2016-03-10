update weak_aura
	set last_update_date = now()
	where id > 0;

update tell_me_when
	set last_update_date = now()
	where id > 0;

update macro
	set last_update_date = now()
	where id > 0;

