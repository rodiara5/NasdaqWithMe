fetch(`/api/v1/nasdaq/industries`)
        .then(response => response.json())
        .then((industryData) => {
            // Extract industry names and corresponding data
            const industryNames = industryData.map(industry => industry.industry);
            const flucValues = industryData.map(industry => industry.avgFluc);
            //const marketCaps = industryData.map(industry => industry.avgMarketCap / 1e12); // Convert to trillions
            const perValues = industryData.map(industry => industry.avgPER);

            // Highcharts 차트 생성
            Highcharts.chart('compareIndustry', {
                chart: {
                    zoomType: 'xy'
                },
                title: {
                    text: '전날 대비 상승률 Top3 산업군',
                    align: 'left'
                },
                xAxis: [{
                    categories: industryNames,
                    crosshair: true,
                    accessibility: {
                        description: 'Top 3 Industry by Fluctuatuon'
                    }
                }],
                yAxis: [{ // Primary yAxis for Market Cap
                    labels: {
                        format: '{value}%',
                        style: {
                            color: Highcharts.getOptions().colors[1]
                        }
                    },
                    title: {
                        text: '등락률 평균 (% 단위)',
                        style: {
                            color: Highcharts.getOptions().colors[1]
                        }
                    }
                }, { // Secondary yAxis for PER
                    title: {
                        text: 'PER',
                        style: {
                            color: Highcharts.getOptions().colors[0]
                        }
                    },
                    labels: {
                        format: '{value}',
                        style: {
                            color: Highcharts.getOptions().colors[0]
                        }
                    },
                    opposite: true
                }],
                tooltip: {
                    shared: true
                },
                series: [{
                    name: '등락률',
                    type: 'column',
                    yAxis: 0,
                    data: flucValues,
                    tooltip: {
                        valueSuffix: ' %'
                    }
                }, {
                    name: 'PER',
                    type: 'spline',
                    yAxis: 1,
                    data: perValues,
                    tooltip: {
                        valueSuffix: ''
                    }
                }]
            });
        })
        .catch(error => console.error('Error fetching data:', error));