/**
 * CARD HOLDER TABLE
 */
CREATE TABLE public.card_holder
(
    id bigint NOT NULL,
    card_number character varying(255) COLLATE pg_catalog."default" NOT NULL,
    exp_date date NOT NULL,
    card_holder_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    system_user_id bigint NOT NULL,
    CONSTRAINT card_holder_pkey PRIMARY KEY (id),
    CONSTRAINT card_holder_card_number_key UNIQUE (card_number)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.card_holder
    OWNER to postgres;
COMMIT;

/**
 * SYSTEM USER TABLE
 */
CREATE TABLE public.system_user
(
    id bigint NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    system_user_profile_id bigint NOT NULL,
    username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT system_user_pkey PRIMARY KEY (id),
    CONSTRAINT system_user_username_key UNIQUE (username)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.system_user
    OWNER to postgres;
COMMIT;

/**
 * SYSTEM USER PROFILE TABLE
 */
    -- Table: public.system_user_profile

-- DROP TABLE public.system_user_profile;

CREATE TABLE public.system_user_profile
(
    id bigint NOT NULL,
    description character varying(255) COLLATE pg_catalog."default" NOT NULL,
    system_user_profile character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT system_user_profile_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.system_user_profile
    OWNER to postgres;
COMMIT;
