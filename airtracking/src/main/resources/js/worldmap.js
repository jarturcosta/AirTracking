(function (d3,topojson) {
    'use strict';
	
	var margin = {top: 0, right: 0, bottom: 0, left: 0},
		width = 960 - margin.left - margin.right,
		height = 500 - margin.top - margin.bottom;
	
    const svg = d3.select('svg');
	
    //d3.geoNaturalEarth1().scale(1100).translate([500,1000])
    //const projection = d3.geoMercator();
	
	// Start Zoom in PT
	const pt = [38.7166700, -9.1333300];
    const projection = d3.geoNaturalEarth1().scale(1100).translate([600,1200]);
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
	
	//ZOOM
	const zoom = d3.zoom()
		.scaleExtent([0.2, 5])
		.translateExtent([worldTopLeft, worldBottomRight])
		.on("zoom", () => {
			g.attr('transform', d3.event.transform);
			g.attr("stroke-width", 1 / d3.event.transform.k);
		}); 
	svg.call(zoom);
	
	// ZONAS DE TESTE
    var places = [
      {
        name: "Porto",
        location: {
          latitude: 41.1496100,
          longitude: -8.6109900
        }
      },
      {
        name: "Lisboa",
        location: {
          latitude: 38.7166700,
          longitude: -9.1333300
        }
      },
      {
        name: "Melbourne",
        location: {
          latitude: -37.8140000,
          longitude: 144.9633200
        }
      },
      {
        name: "Pequim",
        location: {
          latitude: 39.9075000,
          longitude: 116.3972300
        }
      },
      {
        name: "Perth",
        location: {
          latitude: -31.9522400,
          longitude: 115.8614000
        }
      }
    ]
	
    d3.json('https://unpkg.com/world-atlas@1.1.4/world/50m.json')
      .then(data => {
        
        const countries = topojson.feature(data, data.objects.countries);
        
        // countries draw
        g.selectAll('path').data(countries.features)
          .enter()
            .append('path')
            .attr('class', 'country')
            .attr('d', pathGenerator);

        // place dots
        g.selectAll(".pin")
          .data(places)
          .enter()
            .append("circle", ".pin")
            .attr("class", "pin")
            .attr("transform", function(d) {
            return "translate(" + projection([
                d.location.longitude,
                d.location.latitude
              ]) + ")";
            });
	});
  
  }(d3,topojson));