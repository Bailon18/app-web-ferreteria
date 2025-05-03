
DELIMITER $$
DROP PROCEDURE IF EXISTS `BuscarUsuariosPorFiltro` $$
CREATE PROCEDURE `BuscarUsuariosPorFiltro`(
    IN filtro VARCHAR(255)
)
BEGIN
    SELECT 
        *
    FROM 
        user
    WHERE 
        (
            username LIKE CONCAT('%', filtro, '%')
            OR email LIKE CONCAT('%', filtro, '%')
            OR name LIKE CONCAT('%', filtro, '%')
            OR lastname LIKE CONCAT('%', filtro, '%')
            OR document LIKE CONCAT('%', filtro, '%')
        )
        AND is_active = 1;
END $$
DELIMITER ;