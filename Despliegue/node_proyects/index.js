#!/usr/bin/env node
 
const express = require('express')
const app = express()
const port = 8080
 
app.get('/', (request, response) => {
  response.send('<h1>Esto es un titulo</h1>')
})
 
 
app.listen(port, (err) => {
  console.log(`server is listening on ${port}`)
})