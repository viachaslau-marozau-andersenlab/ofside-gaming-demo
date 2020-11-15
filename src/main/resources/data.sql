insert into demo_roles (id, role_name)
    values (1, 'ADMIN'),
           (2, 'USER');

insert into demo_users (id, login, password, role_id)
    values (1000, 'admin', '$2a$10$oxzUhYGL6wx0CTHcZ88VjO5ZFgH7poFXCNUHJ28FqpNpcFdfwrIcu', 1),
           (1001, 'user1', '$2a$10$kZhgCuJK5wb0w97hsthCsOIOAEijptioSA1igzti/IcJsULxaN3WW', 2),
           (1002, 'user2', '$2a$10$0kCE3AYwsncSNervpIJEEuRYrBoQmVEmVc5DPJrpT.haJe3eC23ey', 2),
           (1003, 'user3', '$2a$10$KKvXBmo0kTt80ukjUqnaV.cayshCxT4lpZF12X6v7tNE5tyRuS.4W', 2),
           (1004, 'user4', '$2a$10$kEpm/QdVxhLOuEI8LuIYy.HXjP7jSrzKjYGc8oTfGz5Wa7evffRRe', 2);
