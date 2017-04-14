'use strict';

const fs = require('fs');

function makePancakes () {
  let s = '';
  for (let i = 0; i < 1000; i++) {
    s += Math.random() < 0.5 ? '-' : '+';
  }
  s += ` ${(Math.floor(Math.random() * 99) + 2)}\n`;
  return s;
}

let pancakes = '100\n';
for (let i = 0; i < 100; i++) {
  pancakes += makePancakes();
}
fs.writeFile('1000pancakes_short.txt', pancakes);