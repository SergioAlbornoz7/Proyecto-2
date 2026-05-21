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
    // Obtenir les dades de la base de dades de Civilization_stats (com tenies abans)
    const Civilization_statsRows = await db.query('SELECT name FROM Civilization_stats');
    const Civilization_statsJson = db.table_to_json(Civilization_statsRows, {name: 'string'});
    
    // LA TEVA QUERY AMB ELS JOINS I ALIAS + FILTRE PER A LES 2 ÚLTIMES BATALLES
    // Ordenem per num_battle de forma descendent per agafar les més recents
    const lastBattlesRows = await db.query(`
      SELECT cs.name, bt.civilization_id, bt.num_battle, cs.wood_amount, cs.iron_amount, cs.food_amount, cs.mana_amount 
      FROM Civilization_stats cs 
      JOIN Battle_stats bt ON cs.civilization_id = bt.civilization_id
      ORDER BY bt.civilization_id DESC 
      LIMIT 2
    `);

    // Transformem el resultat de les batalles informant de totes les columnes de la query
    const lastBattlesJson = db.table_to_json(lastBattlesRows, {
      name: 'string',
      civilization_id: 'number',
      num_battle: 'number',
      wood_amount: 'number',
      iron_amount: 'number',
      food_amount: 'number',
      mana_amount: 'number'
    });
    
    // Llegir l'arxiu .json amb dades comunes
    const commonData = JSON.parse(
      fs.readFileSync(path.join(__dirname, 'data', 'common.json'), 'utf8')
    );

    // Construir l'objecte de dades per a la plantilla
    const data = {
      Civilization_stats: Civilization_statsJson,
      Battles: lastBattlesJson, // Enviem les dues últimes batalles amb els camps del JOIN
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
      where bt.civilization_id=${[cursId]}`)

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

/*Batallas*/
app.get('/Batallas', async (req, res) => {
  try {
    // 1. Consulta per obtenir TOTES les batalles barrejades amb el nom de la civilització
    const allBattlesRows = await db.query(`
      SELECT bt.num_battle, bt.civilization_id, cs.name
      FROM Battle_stats bt
      JOIN Civilization_stats cs ON bt.civilization_id = cs.civilization_id
      ORDER BY bt.num_battle DESC
    `);

    // Transformar el resultat a JSON indicant columnes i tipus
    const allBattlesJson = db.table_to_json(allBattlesRows, {
      num_battle: 'number',
      civilization_id: 'number',
      name: 'string'
    });

    // 2. Consulta per a calcular el TOTAL de batalles (per al lloc destacat)
    const totalRows = await db.query('SELECT COUNT(*) AS total FROM Battle_stats');
    // Agafem el valor de la primera fila retornada (si no hi ha res, serà 0)
    const totalBattlesCount = totalRows[0]?.total || 0;
    
    // 3. Llegir l'arxiu .json amb dades comunes
    const commonData = JSON.parse(
      fs.readFileSync(path.join(__dirname, 'data', 'common.json'), 'utf8')
    );

    // 4. Construir l'objecte de dades que rebrà la vista de Handlebars
    const data = {
      Battles: allBattlesJson,
      totalBattles: totalBattlesCount, // Passem la xifra del comptador
      common: commonData
    };

    // Renderitzar la plantilla 'Batallas.hbs'
    res.render('Batallas', data);
  } catch (err) {
    console.error(err);
    res.status(500).send('Error consultant les batalles a la base de dades');
  }
});

app.get('/Civilizacion', async (req, res) => {
  try {
    // Seleccionamos toda la información de recursos, edificios y tecnologías
    const Civilization_statsRows = await db.query(`
      SELECT 
        civilization_id, name, wood_amount, iron_amount, food_amount, mana_amount,
        magicTower_counter, church_counter, farm_counter, smithy_counter, carpentry_counter,
        technology_defense_level, technology_attack_level, battles_counter
      FROM Civilization_stats
    `);

    // Transformamos a JSON mapeando correctamente los tipos de datos
    const Civilization_statsJson = db.table_to_json(Civilization_statsRows, {
      civilization_id: 'number',
      name: 'string',
      wood_amount: 'number',
      iron_amount: 'number',
      food_amount: 'number',
      mana_amount: 'number',
      magicTower_counter: 'number',
      church_counter: 'number',
      farm_counter: 'number',
      smithy_counter: 'number',
      carpentry_counter: 'number',
      technology_defense_level: 'number',
      technology_attack_level: 'number',
      battles_counter: 'number'
    });
    
    // Leer el archivo de datos comunes (menús, configuraciones, etc.)
    const commonData = JSON.parse(
      fs.readFileSync(path.join(__dirname, 'data', 'common.json'), 'utf8')
    );

    // Estructuramos el objeto de datos para Handlebars
    const data = {
      Civilizations: Civilization_statsJson,
      common: commonData
    };

    // Renderizamos la nueva plantilla independiente 'Civilizacion.hbs'
    res.render('Civilizaciones', data);
  } catch (err) {
    console.error(err);
    res.status(500).send('Error consultant la base de dades');
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