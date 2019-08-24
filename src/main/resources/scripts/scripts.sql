-- FUNCTION 
CREATE OR REPLACE FUNCTION fn_list_purchases () 
	RETURNS TABLE (
	 quantity INT,
	 date TEXT
	) 
AS $$
DECLARE 
    var_r record;
BEGIN
FOR var_r IN(
	select (count(*)::int) as quantity, to_char(p.date, 'MM/dd/yyyy') as date from purchase p 
	group by to_char(p.date, 'MM/dd/yyyy') order by to_char(p.date, 'MM/dd/yyyy') asc 
	)  
 LOOP
    quantity := var_r.quantity; 
	date := var_r.date;
    RETURN NEXT;
 END LOOP;
END; $$ 
LANGUAGE 'plpgsql';


-- DATA INTO ROLE TABLE
INSERT INTO role(id_role, name, description) VALUES (1, 'Administrator', 'Administrator');
INSERT INTO role(id_role, name, description) VALUES (2, 'Manager', 'Manager');
INSERT INTO role(id_role, name, description) VALUES (3, 'Operator', 'Operator');

-- DATA INTO MENU TABLE
INSERT INTO menu(id_menu, name, icon, url) VALUES (1, 'Search', 'search', '/search');
INSERT INTO menu(id_menu, name, icon, url) VALUES (2, 'Persons', 'face', '/person');
INSERT INTO menu(id_menu, name, icon, url) VALUES (3, 'Products', 'bookmarks', '/product');
INSERT INTO menu(id_menu, name, icon, url) VALUES (4, 'Purchase', 'shopping_basket', '/purchase');
INSERT INTO menu(id_menu, name, icon, url) VALUES (5, 'Reports', 'assessment', '/reports');

-- DATA INTO MENU_ROLE TABLE
INSERT INTO menu_role(id_menu, id_role) VALUES (1, 1);
INSERT INTO menu_role(id_menu, id_role) VALUES (2, 1);
INSERT INTO menu_role(id_menu, id_role) VALUES (3, 1);
INSERT INTO menu_role(id_menu, id_role) VALUES (4, 1);
INSERT INTO menu_role(id_menu, id_role) VALUES (5, 1);
INSERT INTO menu_role(id_menu, id_role) VALUES (1, 2);
INSERT INTO menu_role(id_menu, id_role) VALUES (2, 2);
INSERT INTO menu_role(id_menu, id_role) VALUES (3, 2);
INSERT INTO menu_role(id_menu, id_role) VALUES (4, 2);
INSERT INTO menu_role(id_menu, id_role) VALUES (5, 2);
INSERT INTO menu_role(id_menu, id_role) VALUES (1, 3);
INSERT INTO menu_role(id_menu, id_role) VALUES (4, 3);
INSERT INTO menu_role(id_menu, id_role) VALUES (5, 3);
