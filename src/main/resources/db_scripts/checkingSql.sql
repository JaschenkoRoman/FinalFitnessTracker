select* from program as pr where " +
                    " creation_time = (select max(creation_time) from program as pr2 " +
                    "where pr.id = pr2.id) AND ORDER BY pr.id;