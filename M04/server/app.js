const express = require('express');
const fs = require('fs');
const path = require('path');
const hbs = require('hbs');
const MySQL = require('./utilsMySQL');

const app = express();
const port = 3000;

// Detectar si estem al Proxmox (si és pm2)
const isProxmox = !!process.env.PM2_HOME;

// Iniciar connexió MySQL
const db = new MySQL();
if (!isProxmox) {
  db.init({
    host: '127.0.0.1',
    port: 3307,
    user: 'super',
    password: '1234',
    database: 'ProyectoMixII'
  });
} else {
  db.init({
    host: '127.0.0.1',
    port: 3307,
    user: 'super',
    password: '1234',
    database: 'ProyectoMixII'
  });
}

// Static files - ONLY ONCE
app.use(express.static('public'))
app.use(express.urlencoded({ extended: true }))

// Disable cache
app.use((req, res, next) => {
  res.setHeader('Cache-Control', 'no-store, no-cache, must-revalidate, proxy-revalidate');
  res.setHeader('Pragma', 'no-cache');
  res.setHeader('Expires', '0');
  res.setHeader('Surrogate-Control', 'no-store');
  next();
});

// Handlebars
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'hbs');

// Registrar "Helpers .hbs" aquí
hbs.registerHelper('eq', (a, b) => a == b);
hbs.registerHelper('gt', (a, b) => a > b);

// Partials de Handlebars
hbs.registerPartials(path.join(__dirname, 'views', 'partials'));

// Route
app.get('/', async (req, res) => {
  try {
    // Obtenir les dades de la base de dades
    const Civilization_statsRows = await db.query('SELECT name FROM Civilization_stats');
    // Transformar les dades a JSON (per les plantilles .hbs)
    // Cal informar de les columnes i els seus tipus
    const Civilization_statsJson = db.table_to_json(Civilization_statsRows, {name: 'string'});
    
    // Llegir l'arxiu .json amb dades comunes per a totes les pàgines
    const commonData = JSON.parse(
      fs.readFileSync(path.join(__dirname, 'data', 'common.json'), 'utf8')
    );

    // Construir l'objecte de dades per a la plantilla
    const data = {
      Civilization_stats:Civilization_statsJson,
      common: commonData
    };

    // Renderitzar la plantilla amb les dades
    res.render('Principal', data);
  } catch (err) {
    console.error(err);
    res.status(500).send('Error consultant la base de dades');
  }
});
//Programadores
app.get('/Programadores', (req, res) => {
  const Programadores = JSON.parse(
    fs.readFileSync(path.join(__dirname, 'data', 'Programadores.json'), 'utf8')
  );

  data = {
    Programadores: Programadores
  }

  res.render('Programadores', data);
});
//Batallas
app.get('/Batallas', async (req, res) => {
  try {
    // Obtenir les dades de la base de dades
    const Civilization_statsRows = await db.query('SELECT name FROM Civilization_stats');
    // Transformar les dades a JSON (per les plantilles .hbs)
    // Cal informar de les columnes i els seus tipus
    const Civilization_statsJson = db.table_to_json(Civilization_statsRows, {name: 'string'});
    
    // Llegir l'arxiu .json amb dades comunes per a totes les pàgines
    const commonData = JSON.parse(
      fs.readFileSync(path.join(__dirname, 'data', 'common.json'), 'utf8')
    );

    // Construir l'objecte de dades per a la plantilla
    const data = {
      Civilization_stats:Civilization_statsJson,
      common: commonData
    };

    // Renderitzar la plantilla amb les dades
    res.render('Batallas', data);
  } catch (err) {
    console.error(err);
    res.status(500).send('Error consultant la base de dades');
  }
});
//Civilizaciones
app.get('/Civilizaciones', async (req, res) => {
  try {
    // Obtenir les dades de la base de dades
    const Civilization_statsRows = await db.query('SELECT name FROM Civilization_stats');
    // Transformar les dades a JSON (per les plantilles .hbs)
    // Cal informar de les columnes i els seus tipus
    const Civilization_statsJson = db.table_to_json(Civilization_statsRows, {name: 'string'});
    
    // Llegir l'arxiu .json amb dades comunes per a totes les pàgines
    const commonData = JSON.parse(
      fs.readFileSync(path.join(__dirname, 'data', 'common.json'), 'utf8')
    );

    // Construir l'objecte de dades per a la plantilla
    const data = {
      Civilization_stats:Civilization_statsJson,
      common: commonData
    };

    // Renderitzar la plantilla amb les dades
    res.render('Civilizaciones', data);
  } catch (err) {
    console.error(err);
    res.status(500).send('Error consultant la base de dades');
  }
});
app.get('/Informes', async (req, res) => {
  try {
    // 1. Afegeix civilization_id a la consulta!
    const Battle_statsRows = await db.query(`
      select cs.name,bt.civilization_id,bt.num_battle
      from Civilization_stats cs
      join Battle_stats bt on cs.civilization_id=bt.civilization_id `);
    
    // 2. Afegeix-lo també aquí per al JSON
    const Battle_statsJson = db.table_to_json(Battle_statsRows, {
      civilization_id: 'number',
      name: 'string',
      num_battle: 'number'
    });
    
    const commonData = JSON.parse(
      fs.readFileSync(path.join(__dirname, 'data', 'common.json'), 'utf8')
    );

    const data = {
      Battle_stats: Battle_statsJson,
      common: commonData
    };

    // Compte: assegura't que el fitxer es diu exactament 'Informes Batallas.hbs'
    // (millor no fer servir espais en els noms de fitxer, però si el tens així, endavant)
    res.render('Informes Batallas', data);
  } catch (err) {
    console.error(err);
    res.status(500).send('Error consultant la base de dades');
  }
});

app.get('/Info', async (req, res) => {
  try {
    // Llegit el valor del paràmetre "id" en format enter
    const cursId = parseInt(req.query.id, 10)

    // Validar que és un número enter positiu (o respondre amb error 400)
    if (!Number.isInteger(cursId) || cursId <= 0) {
      return res.status(400).send('Paràmetre id invàlid')
    }

    // Query only the requested course
    const Battle_statsRows = await db.query(`
      select cs.name,bt.civilization_id,bt.num_battle,cs.wood_amount,cs.iron_amount,cs.food_amount,cs.mana_amount
      from Civilization_stats cs
      join Battle_stats bt on cs.civilization_id=bt.civilization_id
      where bt.num_battle=${[cursId]}`)

    // Si no s'ha trobat cap curs amb aquest id, respondre amb error 404
    if (!Battle_statsRows || Battle_statsRows.length === 0) {
      return res.status(404).send('Curs no trobat')
    }

    // Transformar les dades a JSON (per les plantilles .hbs)
    const Battle_statsJson = db.table_to_json(Battle_statsRows, {
      civilization_id: 'number',
      name: 'string',
      num_battle:'number',
      wood_amount:'number',
      iron_amount:'number',
      food_amount:'number',
      mana_amount:'number'
      
    })

    // Llegir l'arxiu .json amb dades comunes per a totes les pàgines
    const commonData = JSON.parse(
      fs.readFileSync(path.join(__dirname, 'data', 'common.json'), 'utf8')
    )

    // Construir l'objecte de dades per a la plantilla
    // com que tenim una llista amb un sol element, agafem directament el primer element (cursosJson[0])
    const data = {
      Battle: Battle_statsJson[0],
      common: commonData
    }

    // Render a new template (recommended)
    res.render('Informes', data)
  } catch (err) {
    console.error(err)
    res.status(500).send('Error consultant la base de dades')
  }
});

// Start server
const httpServer = app.listen(port, () => {
  console.log(`http://localhost:${port}`);
});

// Graceful shutdown
process.on('SIGINT', async () => {
  await db.end();
  httpServer.close();
  process.exit(0);
});