(function (d3,topojson) {
    'use strict';
  
    const svg = d3.select('svg');

    var margin = {top: 0, right: 0, bottom: 0, left: 0},
      width = svg.node().getBoundingClientRect().width - margin.left - margin.right,
      height = svg.node().getBoundingClientRect().height - margin.top - margin.bottom;
	
    //d3.geoNaturalEarth1().scale(1100).translate([500,1000])
    //const projection = d3.geoMercator();
	
    // Start Zoom in ES
    const countryToFocus = [-3.703790, 	40.416775]; //longitude, latitude

    const projection = d3.geoNaturalEarth1().center(countryToFocus).scale(4000);
    const pathGenerator = d3.geoPath().projection(projection);
    
    const g = svg.append('g')
      .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    g.append('path')
      .attr('class', 'sphere')
      .attr('d', pathGenerator({type: 'Sphere'}));
    
    // PANNING
    var worldBox = svg.node().getBBox();
    const marg = 200;
    const worldTopLeft = [worldBox.x - marg, worldBox.y - marg];
    const worldBottomRight = [
      worldBox.x + worldBox.width + marg,
      worldBox.y + worldBox.height + marg
    ];
    
    // ZONAS DE TESTE
    var places = [
      {
        name: "Madrid",
        location: {
          latitude: 40.416775,
          longitude: -3.703790
        }
      },
      {
        name: "Barcelona",
        location: {
          latitude: 41.390205,
          longitude: 2.154007
        }
      }
    ];
    

    Promise.all([
      d3.tsv('https://unpkg.com/world-atlas@1.1.4/world/50m.tsv'),
      d3.json('https://unpkg.com/world-atlas@1.1.4/world/50m.json')
    ]).then(([tsvData, topoJSONdata]) => {

      const countries = topojson.feature(topoJSONdata, topoJSONdata.objects.countries);
      
      const countryName = {};
      tsvData.forEach(d => {
        countryName[d.iso_n3] = d.name;
      });

      // countries draw
      g.selectAll('path').data(countries.features)
        .enter()
          .append('path')
            .attr('class', 'country')
            .attr('d', pathGenerator)
            .attr('fill', d => colorCountry(countryName[d.id]));

      // place dots
      g.selectAll(".pin")
        .data(places)
        .enter()
          .append("circle", ".pin")
            .attr("class", "pin")
            .attr("r", 6)
            .attr("transform", function(d) {
            return "translate(" + projection([
                d.location.longitude,
                d.location.latitude
              ]) + ")";
            })
          .append("title")
            .text(d => d.name);

    }, {});

    
    function colorCountry(country) {
      
      if (country == "Spain"){
        console.log(country);
        return "grey";
      }
      else {
        return "white";
      }
    }
  
  }(d3,topojson));