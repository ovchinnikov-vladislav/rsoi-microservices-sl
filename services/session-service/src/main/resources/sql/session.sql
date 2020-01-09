CREATE EXTENSION IF NOT EXISTS  "uuid-ossp";
INSERT INTO role (id, name, status, created_at) VALUES (uuid_generate_v4(), 'ROLE_USER', 0, current_date), (uuid_generate_v4(), 'ROLE_ADMIN', 0, current_date) ON CONFLICT (name) DO NOTHING;
INSERT INTO oauth_client (client_id, application_name, client_secret, created_at, updated_at, grant_password, redirect_uri) VALUES ('973db60a-0b2b-44af-9f58-c3b0fe4d175f', 'Front-End', '637d813a-3f1d-47bb-bed9-f2be36ab0507', current_date, current_date, true, ' ') ON CONFLICT (client_id) DO NOTHING;