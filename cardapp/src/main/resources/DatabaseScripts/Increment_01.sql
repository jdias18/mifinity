/**
 * Insert Profiles
 */
INSERT INTO public.system_user_profile(
	id, description, system_user_profile)
	VALUES (1, 'System Administrator', 'ROLE_ADMIN_USER');
	
INSERT INTO public.system_user_profile(
	id, description, system_user_profile)
	VALUES (2, 'System User', 'ROLE_SYSTEM_USER');
COMMIT;
	
/**
 * Insert Admin User
 */
INSERT INTO public.system_user(
	id, password, system_user_profile_id, username)
	VALUES (1, 'admin', 1, 'admin');
	
INSERT INTO public.system_user(
	id, password, system_user_profile_id, username)
	VALUES (2, 'user', 2, 'user');
COMMIT;