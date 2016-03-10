alter table rating_by_user
  add unique index ui_rbu_user_rating (rating_id, user_id);
