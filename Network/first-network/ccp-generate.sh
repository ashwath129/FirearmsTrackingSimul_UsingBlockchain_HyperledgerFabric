#!/bin/bash

function one_line_pem {
    echo "`awk 'NF {sub(/\\n/, ""); printf "%s\\\\\\\n",$0;}' $1`"
}

function json_ccp {
    local PP=$(one_line_pem $4)
    local CP=$(one_line_pem $5)
    sed -e "s/\${ORG}/$1/" \
        -e "s/\${P0PORT}/$2/" \
        -e "s/\${CAPORT}/$3/" \
        -e "s#\${PEERPEM}#$PP#" \
        -e "s#\${CAPEM}#$CP#" \
        ccp-template.json 
}

function yaml_ccp {
    local PP=$(one_line_pem $4)
    local CP=$(one_line_pem $5)
    sed -e "s/\${ORG}/$1/" \
        -e "s/\${P0PORT}/$2/" \
        -e "s/\${CAPORT}/$3/" \
        -e "s#\${PEERPEM}#$PP#" \
        -e "s#\${CAPEM}#$CP#" \
        ccp-template.yaml | sed -e $'s/\\\\n/\\\n        /g'
}

ORG=retail
P0PORT=7051
CAPORT=7054
PEERPEM=crypto-config/peerOrganizations/retail.state.com/tlsca/tlsca.retail.state.com-cert.pem
CAPEM=crypto-config/peerOrganizations/retail.state.com/ca/ca.retail.state.com-cert.pem

echo "$(json_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > connection-retail.json
echo "$(yaml_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > connection-retail.yaml

ORG=firearmsManu
P0PORT=9051
CAPORT=9054
PEERPEM=crypto-config/peerOrganizations/firearmsManu.state.com/tlsca/tlsca.firearmsManu.state.com-cert.pem
CAPEM=crypto-config/peerOrganizations/firearmsManu.state.com/ca/ca.firearmsManu.state.com-cert.pem

echo "$(json_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > connection-firearmsManu.json
echo "$(yaml_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > connection-firearmsManu.yaml

ORG=police
P0PORT=10051
CAPORT=10054
PEERPEM=crypto-config/peerOrganizations/police.state.com/tlsca/tlsca.police.state.com-cert.pem
CAPEM=crypto-config/peerOrganizations/police.state.com/ca/ca.police.state.com-cert.pem

echo "$(json_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > connection-police.json
echo "$(yaml_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > connection-police.yaml

ORG=ncis
P0PORT=8051
CAPORT=8054
PEERPEM=crypto-config/peerOrganizations/ncis.state.com/tlsca/tlsca.ncis.state.com-cert.pem
CAPEM=crypto-config/peerOrganizations/ncis.state.com/ca/ca.ncis.state.com-cert.pem

echo "$(json_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > connection-ncis.json
echo "$(yaml_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > connection-ncis.yaml

ORG=usgovt
P0PORT=12051
CAPORT=12054
PEERPEM=crypto-config/peerOrganizations/usgovt.state.com/tlsca/tlsca.usgovt.state.com-cert.pem
CAPEM=crypto-config/peerOrganizations/usgovt.state.com/ca/ca.usgovt.state.com-cert.pem

echo "$(json_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > connection-usgovt.json
echo "$(yaml_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > connection-usgovt.yaml

ORG=medic
P0PORT=11051
CAPORT=11054
PEERPEM=crypto-config/peerOrganizations/medic.state.com/tlsca/tlsca.medic.state.com-cert.pem
CAPEM=crypto-config/peerOrganizations/medic.state.com/ca/ca.medic.state.com-cert.pem

echo "$(json_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > connection-medic.json
echo "$(yaml_ccp $ORG $P0PORT $CAPORT $PEERPEM $CAPEM)" > connection-medic.yaml
