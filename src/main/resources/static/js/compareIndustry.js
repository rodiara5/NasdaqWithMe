fetch(`/api/v1/nasdaq/industries`)
        .then(response => response.json())
        .then((industryData) => {
            // Extract industry names and corresponding data
            const industryNames = industryData.map(industry => industry.industry);
            const marketCaps = industryData.map(industry => industry.avgMarketCap / 1e12); // Convert to trillions
            const perValues = industryData.map(industry => industry.avgPER);

            // Highcharts 차트 생성
            Highcharts.chart('compareIndustry', {
                chart: {
                    zoomType: 'xy'
                },
                title: {
                    text: 'Top 5 산업군 시가총액 & PER',
                    align: 'left'
                },
                xAxis: [{
                    categories: industryNames,
                    crosshair: true,
                    accessibility: {
                        description: 'Top 5 Industry by Market Cap'
                    }
                }],
                yAxis: [{ // Primary yAxis for Market Cap
                    labels: {
                        format: '{value} 조',
                        style: {
                            color: Highcharts.getOptions().colors[1]
                        }
                    },
                    title: {
                        text: '시가총액 평균 (조 단위)',
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
                    name: '시가총액',
                    type: 'column',
                    yAxis: 0,
                    data: marketCaps,
                    tooltip: {
                        valueSuffix: ' 조'
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