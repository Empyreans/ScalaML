# Almond Scala Jupyter Kernel installation Script
# See https://almond.sh/docs/quick-start-install


# specify versions
SCALA_VERSION=2.11.12
ALMOND_VERSION=0.6.0

# downloads coursier
curl -Lo coursier https://git.io/coursier-cli
# makes it executable
chmod +x coursier
# run it to get the almond launcher of the desired version
./coursier bootstrap \
    --repository jitpack \
    --shared-target  user --isolated user:sh.almond:scala-kernel-api_$SCALA_VERSION:$ALMOND_VERSION \
    sh.almond:scala-kernel_$SCALA_VERSION:$ALMOND_VERSION \
    --output almond
# runs the installation script
./almond --install --id scala211 --display-name "Scala (2.11.12)"

#echo 'You should see a kernel named "scala" :'
#jupyter kernelspec list
jupyter kernelspec list | grep scala && echo "Almond Installed successfully"  || echo "Failed installing Almond"