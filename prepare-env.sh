#!/bin/bash

_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)

function prepare_conda_env() {
  # the python version to use
  local python_version=${1:-3.10}
  shift
  # the conda env name
  local env_name=${1:-cs846mlse}
  shift

  echo ">>> Preparing conda environment \"${env_name}\", python_version=${python_version}"

  # Preparation
  set -e
  eval "$(conda shell.bash hook)"
  conda env remove --name $env_name -y || true
  conda create --name $env_name python=$python_version pip -y
  conda activate $env_name
  pip install --upgrade pip

  # Install libraries
  conda install -y pytorch torchvision torchaudio torchtext torchdata pytorch-cuda=12.4 -c pytorch -c nvidia
  pip install -r requirements.txt
  # pip install -e .[dev]
}

prepare_conda_env $@
