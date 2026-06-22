-- return all the employees with the highest salary
SELECT d.name AS department, e.name AS employee, e.salary 
FROM Employee e
JOIN Department d ON e.departmentId = d.id
WHERE e.salary = (
    SELECT MAX(salary) 
    FROM Employee
    WHERE departmentId = e.departmentId
);
-- return all the employees with the highest salary using window function
-- faster than the previous query
SELECT d.name AS department, e.name AS employee, e.salary 
FROM (
    SELECT *, MAX(salary) OVER (PARTITION BY departmentId) AS max_salary
    FROM Employee
) e
JOIN Department d ON e.departmentId = d.id
WHERE e.salary = e.max_salary;