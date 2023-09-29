@echo off

call tsc ejemplo.ts
call terser ejemplo.js -o ejemplo.min.js

call sass prueba.scss -o prueba_min.css
call cleancss -o prueba_min.css prueba.css

