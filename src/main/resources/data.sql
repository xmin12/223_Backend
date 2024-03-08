--USERS
insert into users (id, email,first_name,last_name, password)
values ('ba804cb9-fa14-42a5-afaf-be488742fc54', 'admin@example.com', 'James','Bond', '$2a$10$TM3PAYG3b.H98cbRrHqWa.BM7YyCqV92e/kUTBfj85AjayxGZU7d6' ), -- Password: 1234
       ('0d8fa44c-54fd-4cd0-ace9-2a7da57992de', 'user@example.com', 'Tyler','Durden', '$2a$10$TM3PAYG3b.H98cbRrHqWa.BM7YyCqV92e/kUTBfj85AjayxGZU7d6') -- Password: 1234
    ON CONFLICT DO NOTHING;


--ROLES
INSERT INTO role(id, name)
VALUES ('d29e709c-0ff1-4f4c-a7ef-09f656c390f1', 'DEFAULT'),
       ('ab505c92-7280-49fd-a7de-258e618df074', 'ADMIN'),
       ('c6aee32d-8c35-4481-8b3e-a876a39b0c02', 'USER')
    ON CONFLICT DO NOTHING;

--AUTHORITIES
INSERT INTO authority(id, name)
VALUES ('2ebf301e-6c61-4076-98e3-2a38b31daf86', 'DEFAULT'),
       ('76d2cbf6-5845-470e-ad5f-2edb9e09a868', 'USER_MODIFY'),
       ('21c942db-a275-43f8-bdd6-d048c21bf5ab', 'USER_DELETE'),
       ('49d95185-a8b2-4925-985f-7be39df08c01', 'MYLISTENTRY_READ'),
       ('c9e2f0c5-3dd5-48a5-902e-21f8983ced95', 'MYLISTENTRY_CREATE'),
       ('3badcd79-e43e-48e1-876d-f645bc2738f8', 'MYLISTENTRY_CREATE_FOR_OTHERS'),
       ('c3ae699e-2961-450e-837c-44adaf28cf25', 'MYLISTENTRY_DELETE'),
       ('21475bf6-6ea6-4ae9-906c-5e3e9ba92c04', 'MYLISTENTRY_UPDATE'),
       ('81c38406-4661-45b4-9026-120970249ca7', 'MYLISTENTRY_CREATE_ADMIN')

    ON CONFLICT DO NOTHING;

--assign roles to users
insert into users_role (users_id, role_id)
values ('ba804cb9-fa14-42a5-afaf-be488742fc54', 'd29e709c-0ff1-4f4c-a7ef-09f656c390f1'),
       ('0d8fa44c-54fd-4cd0-ace9-2a7da57992de', 'c6aee32d-8c35-4481-8b3e-a876a39b0c02'),
       ('ba804cb9-fa14-42a5-afaf-be488742fc54', 'ab505c92-7280-49fd-a7de-258e618df074'),
       ('ba804cb9-fa14-42a5-afaf-be488742fc54', 'c6aee32d-8c35-4481-8b3e-a876a39b0c02'),
       ('ba804cb9-fa14-42a5-afaf-be488742fc54', 'c6aee32d-8c35-4481-8b3e-a876a39b0c02')
    ON CONFLICT DO NOTHING;

--assign authorities to roles
INSERT INTO role_authority(role_id, authority_id)
VALUES ('d29e709c-0ff1-4f4c-a7ef-09f656c390f1', '2ebf301e-6c61-4076-98e3-2a38b31daf86'),
       ('ab505c92-7280-49fd-a7de-258e618df074', '76d2cbf6-5845-470e-ad5f-2edb9e09a868'),
       ('ab505c92-7280-49fd-a7de-258e618df074', '21c942db-a275-43f8-bdd6-d048c21bf5ab'),
       ('ab505c92-7280-49fd-a7de-258e618df074', '49d95185-a8b2-4925-985f-7be39df08c01'),
       ('ab505c92-7280-49fd-a7de-258e618df074', 'c3ae699e-2961-450e-837c-44adaf28cf25'),
       ('ab505c92-7280-49fd-a7de-258e618df074', '81c38406-4661-45b4-9026-120970249ca7'),
       ('ab505c92-7280-49fd-a7de-258e618df074', '3badcd79-e43e-48e1-876d-f645bc2738f8'),
       ('ab505c92-7280-49fd-a7de-258e618df074', '21475bf6-6ea6-4ae9-906c-5e3e9ba92c04'),
       ('c6aee32d-8c35-4481-8b3e-a876a39b0c02', '21c942db-a275-43f8-bdd6-d048c21bf5ab'),
       ('c6aee32d-8c35-4481-8b3e-a876a39b0c02', '49d95185-a8b2-4925-985f-7be39df08c01'),
       ('c6aee32d-8c35-4481-8b3e-a876a39b0c02', 'c9e2f0c5-3dd5-48a5-902e-21f8983ced95')
    ON CONFLICT DO NOTHING;