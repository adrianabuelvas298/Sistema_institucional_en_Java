SELECT  l.*,
        @curRow := @curRow + 1 AS row_number
FROM    Estudiante l
JOIN    (SELECT @curRow := 0) r
where l.Grado_matricula=$P{Grado} and l.Ano_matricula= $P{Ano}  order by l.Apellido_paterno